/***********************************************************************
 * pr.js is part of Touch4j 2.0
 * 
 * Copyright (c) 2012 Emitrom LLC. All rights reserved.
 * 
 * For licensing questions, please contact us at licensing@emitrom.com
 * 
 * -------- Touch4j is released under a commercial license only --------
 *
 * http://www.emitrom.com/license?q=commercialLicense
 ***********************************************************************/

Ext
		.define(
				'Ext.plugin.PullRefresh',
				{
					extend : 'Ext.Component',
					alias : 'plugin.pullrefresh',

					config : {
						/*
						 * @accessor
						 */
						list : null,

						/*
						 * @cfg {String} pullRefreshText The text that will be
						 * shown while you are pulling down. @accessor
						 */
						pullRefreshText : 'Pull down to refresh...',

						/*
						 * @cfg {String} releaseRefreshText The text that will
						 * be shown after you have pulled down enough to show
						 * the release message. @accessor
						 */
						releaseRefreshText : 'Release to refresh...',

						/*
						 * @cfg {String} loadingText The text that will be shown
						 * while the list is refreshing. @accessor
						 */
						loadingText : 'Loading...',

						lastUpdatedText : '',

						/*
						 * @cfg {Number} snappingAnimationDuration The duration
						 * for snapping back animation after the data has been
						 * refreshed @accessor
						 */
						snappingAnimationDuration : 150,

						/*
						 * @cfg {Function} refreshFn The function that will be
						 * called to refresh the list. If this is not defined,
						 * the store's load function will be called. The refresh
						 * function gets called with a reference to this plugin
						 * instance. @accessor
						 */
						refreshFn : null,

						/*
						 * @cfg {XTemplate/String/Array} pullTpl The template
						 * being used for the pull to refresh markup. @accessor
						 */
						pullTpl : [
								'<div class="x-list-pullrefresh">',
								'<div class="x-list-pullrefresh-arrow"></div>',
								'<div class="x-loading-spinner">',
								'<span class="x-loading-top"></span>',
								'<span class="x-loading-right"></span>',
								'<span class="x-loading-bottom"></span>',
								'<span class="x-loading-left"></span>',
								'</div>',
								'<div class="x-list-pullrefresh-wrap">',
								'<h3 class="x-list-pullrefresh-message">{message}</h3>',
								'<div class="x-list-pullrefresh-updated"><span>{lastUpdatedMessage}</span></div>',
								'</div>', '</div>' ].join('')
					},

					isRefreshing : false,
					currentViewState : '',

					initialize : function() {
						this.callParent();

						this.on({
							painted : 'onPainted',
							scope : this
						});
					},

					init : function(list) {
						var me = this, store = list.getStore(), pullTpl = me
								.getPullTpl(), element = me.element, scroller = list
								.getScrollable().getScroller();

						me.setList(list);
						//me.lastUpdated = new Date();

						list.insert(0, me);

						// We provide our own load mask so if the Store is
						// autoLoading already disable the List's mask straight
						// away,
						// otherwise if the Store loads later allow the mask to
						// show once then remove it thereafter
						if (store) {
							if (store.isAutoLoading()) {
								list.setLoadingText(null);
							} else {
								store.on({
									load : {
										single : true,
										fn : function() {
											list.setLoadingText(null);
										}
									}
								});
							}
						}

						pullTpl.overwrite(element, {
							message : me.getPullRefreshText(),
							lastUpdatedMessage : me.getLastUpdatedText()
							//lastUpdated : me.lastUpdated
						}, true);

						me.loadingElement = element.getFirstChild();
						me.messageEl = element
								.down('.x-list-pullrefresh-message');
						me.updatedEl = element
								.down('.x-list-pullrefresh-updated > span');

						me.maxScroller = scroller.getMaxPosition();

						scroller.on({
							maxpositionchange : me.setMaxScroller,
							scroll : me.onScrollChange,
							scope : me
						});
					},

					/**
					 * @private Attempts to load the newest posts via the
					 *          attached List's Store's Proxy
					 */
					fetchLatest : function() {
						var store = this.getList().getStore(), proxy = store
								.getProxy(), operation;

						operation = Ext.create('Ext.data.Operation', {
							page : 1,
							start : 0,
							model : store.getModel(),
							limit : store.getPageSize(),
							action : 'read',
							filters : store.getRemoteFilter() ? store
									.getFilters() : []
						});

						proxy.read(operation, this.onLatestFetched, this);
					},

					/**
					 * @private Called after fetchLatest has finished grabbing
					 *          data. Matches any returned records against what
					 *          is already in the Store. If there is an overlap,
					 *          updates the existing records with the new data
					 *          and inserts the new items at the front of the
					 *          Store. If there is no overlap, insert the new
					 *          records anyway and record that there's a break
					 *          in the timeline between the new and the old
					 *          records.
					 */
					onLatestFetched : function(operation) {
						var store = this.getList().getStore(), oldRecords = store
								.getData(), newRecords = operation.getRecords(), length = newRecords.length, toInsert = [], newRecord, oldRecord, i;

						for (i = 0; i < length; i++) {
							newRecord = newRecords[i];
							oldRecord = oldRecords.getByKey(newRecord.getId());

							if (oldRecord) {
								oldRecord.set(newRecord.getData());
							} else {
								toInsert.push(newRecord);
							}

							oldRecord = undefined;
						}

						store.insert(0, toInsert);
					},

					onPainted : function() {
						this.pullHeight = this.loadingElement.getHeight();
					},

					setMaxScroller : function(scroller, position) {
						this.maxScroller = position;
					},

					onScrollChange : function(scroller, x, y) {
						if (y < 0) {
							this.onBounceTop(y);
						}
						if (y > this.maxScroller.y) {
							this.onBounceBottom(y);
						}
					},

					/**
					 * @private
					 */
					applyPullTpl : function(config) {
						return (Ext.isObject(config) && config.isTemplate) ? config
								: new Ext.XTemplate(config);
					},

					onBounceTop : function(y) {
						var me = this, list = me.getList(), scroller = list
								.getScrollable().getScroller();

						if (!me.isReleased) {
							if (!me.isRefreshing && -y >= me.pullHeight + 10) {
								me.isRefreshing = true;

								me.setViewState('release');

								scroller.getContainer().onBefore({
									dragend : 'onScrollerDragEnd',
									single : true,
									scope : me
								});
							} else if (me.isRefreshing
									&& -y < me.pullHeight + 10) {
								me.isRefreshing = false;
								me.setViewState('pull');
							}
						}
					},

					onScrollerDragEnd : function() {
						var me = this;

						if (me.isRefreshing) {
							var list = me.getList(), scroller = list
									.getScrollable().getScroller();

							scroller.minPosition.y = -me.pullHeight;
							scroller.on({
								scrollend : 'loadStore',
								single : true,
								scope : me
							});

							me.isReleased = true;
						}
					},

					loadStore : function() {
						var me = this, list = me.getList(), scroller = list
								.getScrollable().getScroller();

						me.setViewState('loading');
						me.isReleased = false;

						Ext.defer(function() {
							scroller.on({
								scrollend : function() {
									if (me.getRefreshFn) {
										if (me.getRefreshFn()) {
											me.getRefreshFn().call(me, me);
										}

									} else {
										me.fetchLatest();
									}
									me.resetRefreshState();
								},
								delay : 100,
								single : true,
								scope : me
							});
							scroller.minPosition.y = 0;
							scroller.scrollTo(null, 0, true);
						}, 500, me);
					},

					onBounceBottom : Ext.emptyFn,

					setViewState : function(state) {
						var me = this, prefix = Ext.baseCSSPrefix, messageEl = me.messageEl, loadingElement = me.loadingElement;

						if (state === me.currentViewState) {
							return me;
						}
						me.currentViewState = state;

						if (messageEl && loadingElement) {
							switch (state) {
							case 'pull':
								messageEl.setHtml(me.getPullRefreshText());
								loadingElement.removeCls([
										prefix + 'list-pullrefresh-release',
										prefix + 'list-pullrefresh-loading' ]);
								break;

							case 'release':
								messageEl.setHtml(me.getReleaseRefreshText());
								loadingElement.addCls(prefix
										+ 'list-pullrefresh-release');
								break;

							case 'loading':
								messageEl.setHtml(me.getLoadingText());
								loadingElement.addCls(prefix
										+ 'list-pullrefresh-loading');
								break;
							}
						}

						return me;
					},

					resetRefreshState : function() {
						var me = this;

						me.isRefreshing = false;
						//me.lastUpdated = new Date();

						me.setViewState('pull');
						me.updatedEl.setHtml(me.getLastUpdatedText());
					}
				});

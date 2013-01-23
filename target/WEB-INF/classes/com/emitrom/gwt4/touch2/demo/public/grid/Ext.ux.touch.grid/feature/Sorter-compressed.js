Ext.define("Ext.ux.touch.grid.feature.Sorter",{extend:"Ext.ux.touch.grid.feature.Abstract",requires:"Ext.ux.touch.grid.feature.Abstract",config:{events:{grid:{sort:"updateHeaderIcons"},headerEl:{tap:"handleHeaderTap"}},asc:"x-grid-sort-asc",desc:"x-grid-sort-desc"},onDestroy:function(){var c=this,a=c.grid,d=a.header,b=d.el;b.un({scope:c,tap:c.handleHeaderTap});a.un({scope:c,sort:c.updateHeaderIcons})},isSortable:function(a,b){return !(a.stopSort||b.sortable==false)},handleHeaderTap:function(h,o){h.isStopped=true;var l=this,a=l.getGrid(),g=a.getColumns(),j=0,k=g.length,n=a.getStore(),b=Ext.get(o),m=b.getAttribute("dataindex"),i=n.getSorters(),p=i[0],d=p?p.getDirection():"ASC",f;for(;j<k;j++){f=g[j];if(f.dataIndex===m){break}}if(a.fireEvent("beforesort",a,f)===false||!l.isSortable(a,f)){return}n.sort(m,d==="DESC"?"ASC":"DESC");a.fireEvent("sort")},updateHeaderIcons:function(){var i=this,a=i.getGrid(),l=a.getStore(),h=l.getSorters(),e=a.getHeader(),m=e.element,o=0,f=h.length,k=this.getAsc(),g=this.getDesc(),d,j,b,n,c;i.clearSort();for(;o<f;o++){n=h[o];j=n.getProperty();c=n.getDirection();d=a.getColumn(j);b=d.element;if(!b){b=d.element=Ext.get(m.down("div.x-grid-cell-hd[dataindex="+j+"]"))}b.addCls(c==="DESC"?g:k)}},clearSort:function(){var a=this.getGrid(),e=a.getColumns(),f=a.getHeader(),l=f.element,h=0,i=e.length,k=this.getAsc(),g=this.getDesc(),d,j,b;for(;h<i;h++){d=e[h];j=d.dataIndex;b=d.element;if(!b){b=d.element=Ext.get(l.down("div.x-grid-cell-hd[dataindex="+j+"]"))}b.removeCls(k).removeCls(g)}}});
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<table class="easyui-datagrid" id="itemList" title="信息列表" 
       data-options="singleSelect:false,collapsible:true,pagination:true,url:'/item/list',method:'get',pageSize:30,toolbar:toolbar">
    <thead>
        <tr>
        	<th data-options="field:'ck',checkbox:true"></th>
        	<th data-options="field:'id',width:60">信息ID</th>
            <th data-options="field:'title',width:200">信息标题</th>
            <th data-options="field:'deleteStatus',width:60,align:'center',formatter:E3.formatItemStatus">状态</th>
            <th data-options="field:'updatetime',width:130,align:'center',formatter:E3.formatDateTime">创建日期</th>
        </tr>
    </thead>
</table>
<div id="itemEditWindow" class="easyui-window" title="编辑信息" data-options="modal:true,closed:true,iconCls:'icon-save',href:'/rest/page/message-edit'" style="width:80%;height:80%;padding:10px;">
</div>
<script>

    function getSelectionsIds(){
    	var itemList = $("#itemList");
    	var sels = itemList.datagrid("getSelections");
    	var ids = [];
    	for(var i in sels){
    		ids.push(sels[i].id);
    	}
    	ids = ids.join(",");
    	return ids;
    }
    
    var toolbar = [{
        text:'新增',
        iconCls:'icon-add',
        handler:function(){
        	$(".tree-title:contains('新增信息')").parent().click();
        }
    },{
        text:'编辑',
        iconCls:'icon-edit',
        handler:function(){
        	var ids = getSelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','必须选择一个信息才能编辑!');
        		return ;
        	}
        	if(ids.indexOf(',') > 0){
        		$.messager.alert('提示','只能选择一个信息!');
        		return ;
        	}
        	
        	$("#itemEditWindow").window({
        		onLoad :function(){
        			//回显数据
        			var data = $("#itemList").datagrid("getSelections")[0];
        			$("#itemeEditForm").form("load",data);

        			//加载栏目
                    $.getJSON('/rest/item/query/item/column/'+data.id,function(_data){
                        if(_data.status == 200){
                            //UM.getEditor('itemeEditDescEditor').setContent(_data.data.messageDesc, false);
                            $("#itemeEditForm [name='myColumn']").html(_data.data.name);
                            $("#itemeEditForm [name='cid']").val(_data.data.id);
                        }
                    });
        			// 加载信息描述
        			$.getJSON('/rest/item/query/item/desc/'+data.id,function(_data){
        				if(_data.status == 200){
        					//UM.getEditor('itemeEditDescEditor').setContent(_data.data.messageDesc, false);
        					itemEditEditor.html(_data.data.messageDesc);
        				}
        			});
        			//回显图片
                    $.getJSON('/rest/item/query/item/image/'+data.id,function(_data){
                        if (_data.status == 200) {
                            var imgs = _data.data.images;
                            $.each(imgs,function(index,value) {
                                $("#itemeEditForm [name='image']").after("<a href='" + value.messageAttachmentImage + "' target='_blank'><img src='" + value.messageAttachmentImage + "' width='80' height='50'/></a>");
                            })
                        }
                    });
        			E3.init({
        				"pics" : data.image,
        				"cid" : data.cid,
                        fun:function(node){
                            E3.changeItemParam(node, "itemeEditForm");
                        }
        			});
        		}
        	}).window("open");
        }
    },{
        text:'删除',
        iconCls:'icon-cancel',
        handler:function(){
        	var ids = getSelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','未选中信息!');
        		return ;
        	}
        	$.messager.confirm('确认','确定删除ID为 '+ids+' 的信息吗？',function(r){
        	    if (r){
        	    	var params = {"ids":ids};
                	$.post("/rest/item/delete",params, function(data){
            			if(data.status == 200){
            				$.messager.alert('提示','删除信息成功!',undefined,function(){
            					$("#itemList").datagrid("reload");
            				});
            			}
            		});
        	    }
        	});
        }
    },'-',{
        text:'下架',
        iconCls:'icon-remove',
        handler:function(){
        	var ids = getSelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','未选中信息!');
        		return ;
        	}
        	$.messager.confirm('确认','确定下架ID为 '+ids+' 的信息吗？',function(r){
        	    if (r){
        	    	var params = {"ids":ids};
                	$.post("/rest/item/instock",params, function(data){
            			if(data.status == 200){
            				$.messager.alert('提示','下架信息成功!',undefined,function(){
            					$("#itemList").datagrid("reload");
            				});
            			}
            		});
        	    }
        	});
        }
    },{
        text:'上架',
        iconCls:'icon-remove',
        handler:function(){
        	var ids = getSelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','未选中信息!');
        		return ;
        	}
        	$.messager.confirm('确认','确定上架ID为 '+ids+' 的信息吗？',function(r){
        	    if (r){
        	    	var params = {"ids":ids};
                	$.post("/rest/item/reshelf",params, function(data){
            			if(data.status == 200){
            				$.messager.alert('提示','上架信息成功!',undefined,function(){
            					$("#itemList").datagrid("reload");
            				});
            			}
            		});
        	    }
        	});
        }
    }];
</script>
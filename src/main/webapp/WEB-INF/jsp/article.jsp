<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<link href="<%=path%>/css/bootstrap.min.css" rel="stylesheet">
<script src="<%=path%>/js/wangEditor.min.js"></script>
<script src="<%=path %>/js/jquery-2.1.4.min.js"></script>
<script src="<%=path %>/js/jquery.fullscreen.js"></script>
<style>
	#wrapper{
		    width: 100%;
		    height:100%;
    overflow-x: hidden;
    background-color: white;
	}
</style>
</head>
<body>
	<div  id="wrapper">
	<div class="container">
		<a id="fullScreen">全屏</a>
		<h2>文章发布</h2>
		<form id="publishForm" class="form-horizontal form-label-left">
			<div class="item form-group">
				<div  class="col-md-10 col-sm-10 col-xs-12">
					<input name="title" class="form-control col-md-7 col-xs-12" placeholder="请输入文章名称">
				</div>
			</div>
			<div class="item form-group">
				<div  class="col-md-10 col-sm-10 col-xs-12">
					<input name="image" class="form-control col-md-7 col-xs-12" placeholder="请输入文章封面">
				</div>
			</div>
			<div class="item form-group">
				<div  class="col-md-10 col-sm-10 col-xs-12">
					<div id="articleBox" style="width: 100%;height: 360px;"></div>
	                <textarea class="form-control col-md-7 col-xs-12" id="content" name="content" style="display: none"></textarea>
				</div>
			</div>
			<div class="ln_solid"></div>
			<div class="item form-group">
				<div class="col-md-10 col-sm-10 col-xs-12">
					<button class="btn btn-success js-save">保存</button>
					<button class="btn btn-success js-show">Show</button>
				</div>
			</div>
		</form>

	</div>
	</div>
</body>
<script>
	    var E = window.wangEditor;
        var editor = new E('#articleBox');
        // debug模式下，有 JS 错误会以throw Error方式提示出来
        editor.customConfig.debug = true;
        // 关闭粘贴样式的过滤
        editor.customConfig.pasteFilterStyle = false;
        // 插入网络图片的回调
        editor.customConfig.linkImgCallback = function(url) {
            console.log(url) // url 即插入图片的地址
        };
        editor.customConfig.zIndex = 100;

        var $content = $('#content');
        editor.customConfig.onchange = function (html) {
            // 监控变化，同步更新到 textarea
            $content.val(html);
            console.log(html);
        };

        // 下面两个配置，使用其中一个即可显示“上传图片”的tab。但是两者不要同时使用！！！
        // editor.customConfig.uploadImgShowBase64 = true   // 使用 base64 保存图片
        // 上传图片到服务器
        editor.customConfig.uploadImgServer = '<%=path%>/qiniu/upload';
        editor.customConfig.uploadFileName = 'file';
        // 将图片大小限制为 5M
        editor.customConfig.uploadImgMaxSize = 5 * 1024 * 1024;
        editor.customConfig.customAlert = function (info) {
            // info 是需要提示的内容
            $.alert.error(info);
        }
        editor.customConfig.uploadImgHooks = {
            error: function (xhr, editor) {
                alert("图片上传出错");
            },
            timeout: function (xhr, editor) {
                alert("请求超时");
            },
            customInsert: function (insertImg, result, editor) {
                // 图片上传并返回结果，自定义插入图片的事件（而不是编辑器自动插入图片！！！）
                // insertImg 是插入图片的函数，editor 是编辑器对象，result 是服务器端返回的结果
               	console.log(result)
               	console.log('customInsert：' + insertImg, result, editor);
               	if(result.status == 0){
                    console.log(result.data);
                    var imgFullPath = result.data;
                    // editor.txt.append(' <a href="' + imgFullPath + '" class="showImage" title="" rel="external nofollow"><img src="' + imgFullPath + '" class="img-responsive  img-rounded" alt="" style="width: 95%;"/></a>');
                    editor.txt.append('<img src="' + imgFullPath + '" alt="" style="border-radius: 6px;"/>');
                } else {
                    alert(result.message);
                }
            }
        };
		
        editor.create();
        console.log()
       
        $('#publishForm').find('.js-show').on('click', function (){
        	alert(editor.txt.html())
        	
        }) 
        $('#publishForm').find('.js-save').on('click', function (){
        	$.ajax({
        		url:'<%=path %>/article/save',
        		data:$('#publishForm').serialize()
        	}).
        	done(function (res){
        		console.log(res)
        	})
        	
        }) 
        
    // 全屏显示
    $('#fullScreen').on('click', function () {
    	$('#wrapper').fullScreen();
    });
</script>
</html>

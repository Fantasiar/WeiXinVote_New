$(function(){
	
	var index = location.href.lastIndexOf("=");
	var aid= location.href.substr(index+1);
	
	$("#aid").val(aid);
	
	var files = [];
	
	$("#photo").change(function(){
		var file = this.files[0];
		//把当前文件追加到formData对象
		files.push(file);
		//文件预览
		//1. 创建一个filereader对象
		var fileReader = new FileReader();
		//2. 定义filereader的onload方法（base64字符串）
		    //append(<img src="base64字符串">)
		fileReader.onload = function(e)
		{
			var base64 = e.target.result;
			$("#photos").append('<img class="addedphoto" src="'+base64+'"/>');
		}
		//3. 读
		fileReader.readAsDataURL(file);
	});
	
	//提交报名
	$("#btn_baoming").click(function(){
		//1. 创建一个formdata对象
		var formData = new FormData(document.getElementById("myform"));
		//2. 把文件追加到formData中
		for(var i=0; i<files.length;i++)
		{
			formData.append("upload",files[i]);
		}
		
		//提交ajax
		$.ajax({		
			url:"candidates",
			type:"post",
			data:formData,
			dataType:"json",
			contentType:false,
			processData:false,
			cache:false,
			success:function(data)
			{
				//{result:true}
				if(data.result)
				{
					alert("报名成功");
				}
				else
				{
					alert("报名失败");
				}
			}		
		});
	});
	
	
});


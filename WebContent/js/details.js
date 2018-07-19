$(function(){
	
	var index = location.href.lastIndexOf("=");
	var cid= location.href.substr(index+1);
	
	$.ajax({
		url:"candidate/"+cid,
		type:"get",
		dataType:"json",
		success:function(data)
		{
			console.log(data);
			//imgurl & cname
			$("#imgurl").attr("src","img/"+data.imgurl);
			$("#cname").html(data.cname);
			$("#cid").html(data.cid);
			$("#tickets").html(data.tickets);
			$("#hots").html(data.hots);
			$("#gifts").html(data.gifts);
			$("#declaration").html(data.cdeclaration);
			//更新图片
			for(var i=0;i<data.images.length;i++)
			{
				$("#images").append('<img src="img/'+data.images[i].imgurl+'" />')
			}
			
			//把data放在本地存储里
		    //在js中，对象-》json字符串互转 JSON 
			//JSON.parse() 字符串-》对象
			//JSON.stringify() 对象-》字符串
			sessionStorage.c = JSON.stringify(data);
			
			
		}
	});
	
});
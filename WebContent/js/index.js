$(function(){
	var index = location.href.lastIndexOf("=");
	var aid= location.href.substr(index+1);
	
	//获得活动的信息
	$.ajax({
		url:"activity/"+aid,
		type:"get",
		dataType:"json",
		success:function(data)
		{
			//更新活动图片
			$("#banner").attr("src","img/"+data.imgurl);
			//更新活动数据
			$("#totalpeople").html(data.totalpeople);
			$("#totaltickets").html(data.totaltickets);
			$("#totalaccess").html(data.totalaccess);
			
			//扩大作用域为全局变量
			endtime = data.endtime;
			
			//更新倒计时
			var now = new Date();
			
			if(now<data.starttime)
			{
				//1. 如果当前时间小于开始时间-活动未开始
				$("#status").html("活动未开始");
			}
			else if(now>=data.endtime)
			{
				//2. 如果当前时间大于结束时间-活动已结束
				$("#status").html("活动已结束");
			}
			else
			{
				//3. 活动进行时
				$("#status").html("活动倒计时");
			    taskid = setInterval(countdown,1000);
			}
			
			//倒计时
			function countdown()
			{
				var now = new Date();
			
				if(now>=endtime)
				{
					//终止循环任务
					clearInterval(taskid);
				}
				else
				{
					//1. 获得结束时间-当前时间(毫秒数)
					var diff = endtime - now;
					//2. 根据毫秒数算天，小时，分钟，秒
					var seconds = padLeft(parseInt(diff/1000%60));
					var minutes = padLeft(parseInt(diff/1000/60%60));
					var hours = padLeft(parseInt(diff/1000/60/60%24));
					var days = parseInt(diff/1000/60/60/24);
					$("#countdown").html(days+'天 '+hours+'小时 '+minutes+'分钟 '+seconds+'秒');
				}
			}
			
			function padLeft(num)
			{
				if(num<10)
				{
					return "0"+num;
				}
				return num;
			}
		}
		
		

	});
	
	var pageNum = 1;
	//调用方法
	loadcandidate();
	
	//定义方法
	function loadcandidate()
	{
		//获得参赛选手的信息
		$.ajax({
			url:"candidates/"+aid+"/"+(pageNum++),
			type:"get",
			dataType:"json",
			success:function(data)
			{
				if(data.length==0)
				{
					$("#loadmore").html("没有更多数据了");
				}
				else
				{
					for(var i=0; i<data.length;i++)
					{
						var str = '<div class="c" data-id="'+data[i].cid+'">';
						str+='<img src="img/'+data[i].imgurl+'" />';
						str+='<div>'+data[i].cid+'号,'+data[i].tickets+'票</div>';
						str+='<p class="cp">'+data[i].cname+'</p>';
						str+='</div>';
						
						$("#div2_2").append(str);
					}
				}				
			}
			
		});
	}
	
	$("#loadmore").click(function(){
		loadcandidate();
	});
	
	$("#search_btn").click(function(){
		var name = $("#search_text").val();
		if(name == null || name.length==0)
		{
			alert("查询条件不能为空");
		}
		else
		{
			//隐藏加载更多
			$("#loadmore").css("display","none");
			$.ajax({
				
				url:"candidates/"+name,
				type:"post",
				dataType:"json",
				success:function(data)
				{
					console.log(data);
					//1.清除原有的数据
					$("#div2_2").empty();
					//2.追加符合条件的数据
					for(var i=0; i<data.length;i++)
					{
						var str = '<div class="c"> data-id='+data[i].cid+'  ';
						str+='<img src="img/'+data[i].imgurl+'" />';
						str+='<div>'+data[i].cid+'号,'+data[i].tickets+'票</div>';
						str+='<p>'+data[i].cname+'</p>';
						str+='</div>';
						
						$("#div2_2").append(str);
					}
				}
			});
		}
		
	});
	
	$("#btn_baoming").click(function(){
		location.href="baoming.html?aid="+aid;
	});
	
	
	$(document).on("click",".c",function(){
		var cid=$(this).attr("data-id");
		location.href="details.html?cid="+cid;
	});
	
	
});










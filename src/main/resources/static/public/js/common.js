var xsui = {
	
	//设置公用头部
	setHeader:function(){
		var strHtml = '';
    	
    	//头部导航
		strHtml +='<div class="jzy_w_max jzy_h_50 br_12">';
    	//LOGO
    	strHtml +='<div class="jzy_w_50 jzy_h_50 br_12 br_11_hove lt logo">';
        strHtml +='<a href=""><img src="public/images/aliyun.png"></a>';
    	strHtml +='</div>';
    	//菜单1
    	strHtml +='<div class="jzy_w_90 jzy_h_50 br_12 br_11_hove lt top-txt">';
        strHtml +='<a class="cr_h_0 fs_14" href="./index.html">养护巡查</a>';
    	strHtml +='</div>';

    	//头像
    	strHtml +='<div class="jzy_w_50 jzy_h_50 br_12 br_11_hove rt top-txt logo">';
        strHtml +='<img class="jzy_boy_18" src="public/images/touxiang.jpg" width="36" height="36">';
        //头像下拉菜单
        strHtml +='<div class="jzy_w_150 jzy_h_158 br_h_0 top_nav">';
            //头像跟用户名
        strHtml +='<div class="jzy_w_max jzy_h_50 lt" style="margin-top: 15px"><img class="jzy_boy_18" src="public/images/touxiang.jpg" width="36" height="36"></div>';
        strHtml +='<div class="jzy_w_max jzy_h_30 lt" style="line-height: 30px; border-bottom: 1px solid #e2e2e2" id="menuUserInfo"></div>';
        strHtml +='<div class="jzy_w_max jzy_h_42 lt br_h_9" style="background: #f5f5f6; line-height: 45px"><a href="login.html">退出管理控制台</a></div>';
        strHtml +='</div>';
    	strHtml +='</div>';

    	// //菜单3
    	strHtml +='<div class="jzy_w_90 jzy_h_50 br_12 br_11_hove rt top-txt">';
        strHtml +='<a class="cr_h_0 fs_14" href="./index.html">首页</a>';
        // //头像下拉菜单
        // strHtml +='<div class="jzy_w_90 br_h_0 top_nav nav2">';
        // strHtml +='<p class="p"><a href="./M000901.html">新建权限</a></p>';
        // strHtml +='<p class="p"><a href="">角色列表</a></p>';
        // strHtml +='<p class="p"><a href="">部门管理</a></p>';
        strHtml +='</div>';
     	// strHtml +='</div>';
		strHtml +='</div>';
		$('body').prepend(strHtml);	
	},

	//设置公用左侧菜单
	setLeftMenu:function(){
		var strHtml = '';
		//左侧主导航
		strHtml +='<div class="jzy_w_220 lt main_height main_left nav" style="background: #333744">';
		strHtml +='<ul>';

		$.ajax({
			type: 'get',
			async: false,
			url: './m0001/user/menu?random=' + Math.random(),
			success: function (ret) {
			  if (ret.code != 0) {
				alert(ret.msg);
			  } else {
				if (ret.data && ret.data.menu && ret.data.menu.length > 0) {
					$.each(ret.data.menu, function (i, record) {
						strHtml +='<li class="nav-item">';
						// if (record.text === '首页') {
						// 	strHtml +='<a href="./index.html"><i class="my-icon nav-icon icon_1"></i><span>' + record.text + '</span></a>';
						// } else {
							strHtml +='<a href="javascript:;"><i class="my-icon nav-icon icon_1"></i><span>' + record.text + '</span><i class="my-icon nav-more"></i></a>';
						// }
						strHtml +='<ul>';
						$.each(record.children, function (j, subrecord) {
							strHtml +='<li><a href=".'+ subrecord.url + '"><span>' + subrecord.text + '</span></a></li>';
						})
						strHtml +='</ul>';
						strHtml +='</li>';
					});
				}
			  }
			  $("#menuUserInfo").html(ret.data.userName);
			},
			error: function (e) {
			  if (e.status === 401) {
				window.location.href = './login.html'
			  } else {
				alert('系统发生错误，请联系管理员！');
			  }

			}
		});
    	strHtml +='</ul>';
		strHtml +='</div>';
		$('body').prepend(strHtml);	
	},
	
	
	ajax: function(opts) {
		opts = opts || {};
		opts.url = 'url' in opts ? opts.url : "";
		opts.async = 'async' in opts ? opts.async : true;
		opts.type = 'type' in opts ? opts.type.toUpperCase() : 'GET';
		opts.dataType = 'dataType' in opts ? opts.dataType : 'json';
		opts.beforeSend = 'beforeSend' in opts ? opts.beforeSend : mui.noop();
		opts.complete = 'complete' in opts ? opts.complete : null;
		opts.success = 'success' in opts ? opts.success : mui.noop();
		opts.error = 'error' in opts ? opts.error : mui.noop();
		opts.timeout = 'timeout' in opts ? opts.timeout : 30 * 1000;
		opts.data = 'data' in opts ? opts.data : {};

		$.ajax(xsui.url + opts.url, {
			data: opts.data,
			dataType: opts.dataType,
			type: opts.type,
			timeout: opts.timeout,

			xhrFields: {
				withCredentials: false
			},
			crossDomain: true,
			beforeSend: function() {
				//xsui.showLoading();
			},
			success: function(data) {
				if(opts.success) {
					//xsui.hideLoading();
					opts.success(data);
				}
			},
			error: function(xhr, type, errorThrown) {
				xsui.hideLoading();
				xsui.tools.toast({
					msg: type
				});
			}
		})
	},
	tools: {
		isEmpty: function(param) {
			if(param == null || param == "" || typeof(param) == "undefined" || param == "undefined") {
				return true;
			}
			return false;
		}
	}
}
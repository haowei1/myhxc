(function ($) {

    //备份jquery的ajax方法

    var _ajax = $.ajax;
    //重写jquery的ajax方法
    $.ajax = function (opt) {
        //备份opt中error和success方法
        var fn = {
            error: function (XMLHttpRequest, textStatus, errorThrown) { },
            success: function (data, textStatus) { }
        }
        if (opt.error) {
            fn.error = opt.error;
        }

        if (opt.success) {
            fn.success = opt.success;
        }
        //扩展增强处理
        var _opt = $.extend(opt, {
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                //错误方法增强处理
                if (XMLHttpRequest.status === 401 || XMLHttpRequest.status === 403) {
                    top.window.location.href = '/yhxc/login.html'
                }
                fn.error(XMLHttpRequest, textStatus, errorThrown);
            },

            success: function (data, textStatus, xhr) {
                //成功回调方法增强处理
                fn.success(data, textStatus);
            }
        });
        _ajax(_opt);

    };
})(jQuery);

function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]); return null;
}

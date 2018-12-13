var oldpassword = null;
var newpassword = null;
var rnewpassword = null;
var saveBtn = null;

$(function () {
    oldpassword = $("#oldpassword");
    newpassword = $("#newpassword");
    rnewpassword = $("#rnewpassword");
    saveBtn = $("#save");
    oldpassword.next().html("*");
    newpassword.next().html("*");
    rnewpassword.next().html("*");

    // 原始密码的判断
    oldpassword.on("blur", function () {
        $.ajax({
            type: "get",
            url: "/user/pwdModify",
            data: {oldpassword: oldpassword.val()},
            dataType: "json",
            success: function (data) {
                // console.log(data);
                if (data.result == "error") {//旧密码正确
                    // validateTip(oldpassword.next(), {"color": "green"}, imgYes, true);
                    // oldpassword.next().html(data.msg);
                    validateTip(oldpassword.next(), {"color": "red"}, imgNo + " 请输入旧密码", false);
                } else if (data.result == "false") {
                    // oldpassword.next().html(data.msg);
                    validateTip(oldpassword.next(), {"color": "red"}, imgNo + " 原密码输入不正确", false);
                } else if (data.result == "sessionerror") {
                    // oldpassword.next().html(data.msg);
                    validateTip(oldpassword.next(), {"color": "red"}, imgNo + " 当前用户session过期，请重新登录", false);
                } else {
                    // oldpassword.next().html(data.msg);
                    validateTip(oldpassword.next(), {"color": "green"}, imgYes, true);
                }
                /*else if (data.result=="false") {//旧密码输入不正确
                    validateTip(oldpassword.next(), {"color": "red"}, imgNo + " 原密码输入不正确", false);
                } else if (data.result=="sessionerror") {//当前用户session过期，请重新登录
                    validateTip(oldpassword.next(), {"color": "red"}, imgNo + " 当前用户session过期，请重新登录", false);
                } else if (data.result=="error") {//旧密码输入为空
                    validateTip(oldpassword.next(), {"color": "red"}, imgNo + " 请输入旧密码", false);
                }*/
            },
            error: function (data) {
                //请求出错
                validateTip(oldpassword.next(), {"color": "red"}, imgNo + " 请求错误", false);
            }
        });

    }).on("focus", function () {
        validateTip(oldpassword.next(), {"color": "#666666"}, "* 请输入原密码", false);
    });

    // 新密码的判断
    newpassword.on("blur",function () {
        // newpassword.next().html("密码长度必须是大于6小于20");
        if (newpassword.val() !== null && newpassword.val().length > 6
            && newpassword.val().length < 20) {
            validateTip(newpassword.next(), {"color": "green"}, imgYes, true);
        } else {
            validateTip(newpassword.next(), {"color": "red"}, imgNo + " 密码输入不符合规范，请重新输入", false);
        }
    }).on("focus",function () {
        validateTip(newpassword.next(), {"color": "#666666"}, "* 密码长度必须是大于6小于20", false);
    })

    //新旧密码判断
    rnewpassword.on("focus", function () {
        validateTip(rnewpassword.next(), {"color": "#666666"}, "* 请输入与上面一致的密码", false);
    }).on("blur", function () {
        if (rnewpassword.val() != null && rnewpassword.val().length > 6
            && rnewpassword.val().length < 20 && newpassword.val() == rnewpassword.val()) {
            validateTip(rnewpassword.next(), {"color": "green"}, imgYes, true);
        } else {
            validateTip(rnewpassword.next(), {"color": "red"}, imgNo + " 两次密码输入不一致，请重新输入", false);
        }
    });

    // 修改密码操作
    saveBtn.on("click", function () {
        oldpassword.blur();
        newpassword.blur();
        rnewpassword.blur();
        if (oldpassword.attr("validateStatus") == "true"
            && newpassword.attr("validateStatus") == "true"
            && rnewpassword.attr("validateStatus") == "true") {
            if (confirm("确定要修改密码？")) {
                $("#userForm").submit();
            }
        }

    });
});
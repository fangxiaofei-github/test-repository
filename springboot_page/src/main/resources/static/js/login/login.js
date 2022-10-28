
//从layui2.6开始，如果你引入的是构建后的layui.js，里面即包含了layui所有的内置模块，无需再指定内置模块(form)。
layui.use('form', function(){
    var form = layui.form; // 只有执行了这一步，部分表单元素才会自动修饰成功
    var $= layui.jquery; // 获取jquery对象
    var layer = layui.layer; //弹出框

    //==初始化验证码(第一步)==
    var verCode = new GVerify({
        id: "verify-img", //容器id
        type: "number"    //图形验证码的类型：blend-数字字母混合类型（默认）、number-纯数字、letter-纯字母
    });

    //==刷新验证码(第二步)==
    $(document).on('click', "#verify-img", function () {
        verCode.refresh();
    });


    //提交表单
    form.on('submit(user-login-submit)', function (obj) {
        //获取用户名
        var username = obj.field.username;
        //获取密码
        var password = obj.field.password;
        //获取验证码
        var code = obj.field.vercode;
        //获取记住密码的状态
        var remember = obj.field.remember;

        //==校验验证码(第三步)==
        var flag = verCode.validate(code);
        if (flag == false) {
            $("#LAY-user-login-vercode").val("");
            // document.getElementById("LAY-user-login-vercode").value = ''; //它的效果和上面的一样
            layer.msg('登录失败，验证码错误！', {
                offset: '15px',
                icon: 2,
                time: 1000
            });
        } else {
            //发送ajax请求给后台接口
            $.ajax({
                url: "http://localhost:8888/zul/auth/user/login",
                sync: "true", //默认值为true
                type: "post",
                contentType: "application/json;charset=utf-8", //默认值: "application/x-www-form-urlencoded"
                data: JSON.stringify(obj.field),
                // data: {
                //     username: username,
                //     password: password,
                //     remember: remember
                // },
                dataType: "json", //默认值为string。表示预期的服务器响应的数据类型。
                success: function (data) {

                    //登录成功(code=200)则进入主页。登录失败(code=500)则返回登录页并且自动填充账号和密码
                    if (data.code == 500){
                        // 登录失败(code=1)则返回登录页并且自动填充账号和密码
                        // $("#username").val(data.data.username);
                        // $("#password").val(data.data.password);
                        // 提示框（默认3秒后自动关闭窗口）。注意这个提示框不会重置输入框中内容相当于自动填充账号和密码
                        // layer.msg( data.msg, //提示信息
                        //     {
                        //         icon: 5, // 图标（5表示不开心表情）
                        //         time: 1000 //1秒关闭（如果不配置，默认是3秒）
                        //     }
                        // )
                        layer.msg(data.msg, {
                            offset: '15px',
                            icon: 2,
                            time: 1500
                        });
                    }
                    if (data.code == 200){
                        //把后端传递的token进行储存
                        localStorage.setItem("token",data.data.token);
                        //还有把用户名存储起来
                        sessionStorage.setItem("username",username);
                        //把当前用户id存储起来
                        sessionStorage.setItem("userId",data.data.userId);

                        //登录成功则跳转到主页(location.href=资源路径)
                        // 在js文件中它是以html文件为基准的（谷歌游览器）
                        window.location.href = 'layuiMini/page/index.html';
                    }

                }


            })

        }


    });

    //如果你的 HTML 是动态生成的，自动渲染就会失效
    //因此你需要在相应的地方，执行下述方法来进行渲染
    form.render();
});


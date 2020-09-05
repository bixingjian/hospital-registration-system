var main=new Vue({
    el: '#logobox',
    data: {
        user:
            {
                username:'',
                password:''
            },
        layer:null
    },
    mounted:function()
    {
        var _this = this;
        layui.use('layer', function(){
            var layer = layui.layer;
            _this.layer=layer;
        });
    },
    methods: {
        login:function () {
            var _this = this;
            var layer = _this.layer;
            if(_this.user.password=="" || _this.user.username==""){
                layer.alert("用户名或者密码不得为空");
            }
            $.ajax({
                url: "submitLogin",
                type: "post",
                contentType: "application/json",
                data:JSON.stringify(_this.user),
                success: function (result) {
                    if(result.username=="")
                    {
                        layer.alert("登录失败");
                    }else if(result.type==0)
                        {

                            location.href = 'doctor';
                        }
                    else if(result.type==1)
                    {

                        location.href = 'doctorDo';
                    }else if(result.type==2)
                    {

                        location.href = 'registration';
                    }
                }
            });
        }

    }
});
var main=new Vue({
    el: '#main',
    data: {
        param:
            {
                name:''
            },
        tableInfo:[],
        layer:null
    },
    mounted:function()
    {
        var _this = this;
        _this.init(true);
        layui.use('layer', function(){
            var layer = layui.layer;
            _this.layer=layer;
        });
    },
    methods: {
        init:function (isPage) {
            var _this = this;
            $.ajax({
                url: "doctorDo/findInfo",
                type: "post",
                contentType: "application/json",
                data:JSON.stringify(_this.param),
                success: function (result) {
                    _this.tableInfo= result;
                    if(isPage)
                    {
                        $.ajax({
                            url: "doctorDo/countInfo",
                            type: "post",
                            contentType: "application/json",
                            data:JSON.stringify(_this.param),
                            success: function (result) {
                                _this.initPage(result);
                            }
                        });
                    }
                }
            });

        },
        initPage:function (pageSize) {
            var _this = this;
            layui.use('laypage', function(){
                var laypage = layui.laypage;
                laypage.render({
                    elem: 'page' //注意，这里的 page 是 ID，不用加 # 号
                    ,count:pageSize
                    ,limit:_this.param.pageSize,
                    jump: function(obj, first){
                        if(first!=true)
                        {
                            _this.param.pageNum = obj.curr;
                            _this.init(false);
                        }

                    }
                });
            });
        },
        edit:function (item) {
            var _this = this;
            var layer = _this.layer;
            var param=JSON.parse(JSON.stringify(item));
            var showTitle = "确认重启问诊？";
            if(item.finish==0)
            {
                showTitle = "确认问诊结束？";

            }
            layer.confirm(showTitle, {
                skin: 'confirm_layer',
                btn: ['确定', '取消'],
                closeBtn: 0 //按钮
            }, function() {
                if(item.finish==0)
                {
                    param.finish=1
                }else
                    {
                        param.finish=0
                    }
                $.ajax({
                    url: "doctorDo/updateFinsh",
                    type: "post",
                    contentType: "application/json",
                    data:JSON.stringify(param),
                    success: function (result) {
                        if(result=="success")
                        {
                            layer.closeAll();
                            _this.init(true);
                            layer.alert("更新成功");
                        }
                    }
                });
            });


        },
        closeWin:function () {
            var _this = this;
            var layer = _this.layer;
            layer.close(layer.index);
        },
    }
});
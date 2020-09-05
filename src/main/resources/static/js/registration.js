var main=new Vue({
    el: '#main',
    data: {
        param:
            {

                doctorName:'',
                departmentName:'',
                name:''
            },
        newParam:
            {
                name:'',
                sex:'',
                birthday:'',
                age:'',
                idCard:'',
                address:'',
                type:'',
                doctorName:'',
                departmentName:''
            },
        editParam:
            {
                name:'',
                sex:'',
                birthday:'',
                age:'',
                idCard:'',
                address:'',
                type:'',
                doctorName:'',
                departmentName:''
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
                url: "registration/findInfo",
                type: "post",
                contentType: "application/json",
                data:JSON.stringify(_this.param),
                success: function (result) {
                    _this.tableInfo= result;
                    if(isPage)
                    {
                        $.ajax({
                            url: "registration/countInfo",
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
                    elem: 'page'
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
        addNewInfo:function () {
            var _this = this;
            var layer = _this.layer;
            _this.newParam=
                {
                    doctorName:'',
                    departmentName:'',
                    num:0
                },

                layer.open({
                    title: '添加',
                    type: 1,
                    offset:'50px',
                    skin: 'node_layer',
                    area: ['400px', '500px'],
                    content: $('#newInfo')
                });
        },
        addNew:function () {
            var _this = this;
            var layer = _this.layer;
            $.ajax({
                url: "registration/addNew",
                type: "post",
                contentType: "application/json",
                data:JSON.stringify(_this.newParam),
                success: function (result) {
                    if(result=="error1")
                    {
                        layer.alert("科室不存在");
                        return
                    }
                    if(result=="error2")
                    {
                        layer.alert("该科室下没有该医生");
                        return
                    }
                    if(result=="error3")
                    {
                        layer.alert("该医生挂号人数已满");
                        return
                    }
                    if(result=="error4")
                    {
                        layer.alert("请填写正确的生日日期");
                        return
                    }
                    layer.closeAll();
                    _this.init(true);
                    layer.alert("添加成功");
                }
            });
        },
        del:function (id) {
            var _this = this;
            var layer = _this.layer;
            layer.confirm("确定删除该信息吗？", {
                skin: 'confirm_layer',
                btn: ['确定', '取消'],
                closeBtn: 0 //按钮
            }, function() {
                $.ajax({
                    url: 'registration/delete',
                    type:'post',
                    contentType: "application/json",
                    data:JSON.stringify({"id":id}),
                    success: function(result){

                        layer.closeAll();
                        _this.init(true);
                        layer.alert("删除成功");
                    }});
            });
        },
        edit:function (item) {
            var _this = this;
            var layer = _this.layer;
            _this.editParam=JSON.parse(JSON.stringify(item))
            layer.open({
                title: '编辑',
                type: 1,
                offset:'50px',
                skin: 'node_layer',
                area: ['400px', '550px'],
                content: $('#editInfo')
            });
        },
        update:function () {
            var _this = this;
            var layer = _this.layer;
            $.ajax({
                url: "registration/update",
                type: "post",
                contentType: "application/json",
                data:JSON.stringify(_this.editParam),
                success: function (result) {
                    if(result=="error1")
                    {
                        layer.alert("科室不存在");
                        return
                    }
                    if(result=="error2")
                    {
                        layer.alert("该科室下没有该医生");
                        return
                    }
                    if(result=="error3")
                    {
                        layer.alert("该医生挂号人数已满");
                        return
                    }
                    if(result=="error4")
                    {
                        layer.alert("请填写正确的生日日期");
                        return
                    }
                    layer.closeAll();
                    _this.init(true);
                    layer.alert("更新成功");
                }
            });
        },
        closeWin:function () {
            var _this = this;
            var layer = _this.layer;
            layer.close(layer.index);
        },
    }
});
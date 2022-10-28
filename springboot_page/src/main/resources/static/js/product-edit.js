
$(
    function () {
        let productId = sessionStorage.getItem("productId");
        // let productId = $("#productId").val();
        console.log("pId="+productId);
        $.ajax({
            url: "http://localhost:8888/zul/other/findProductById",
            data: {"id":productId},
            // 请求头携带token
            headers: {
                "token":localStorage.getItem("token")
            },
            type:'get',
            dataType:'json',
            sync:false,

            success:function (result) {
                layui.use(['form', 'layer','layedit'],
                    function() {
                        var form = layui.form,
                            layer = layui.layer;
                        var layedit = layui.layedit;
                        if (result.code == 200) {

                            form.val('update-form',result.data);

                            $("#fwb").val(result.data.describe);

                            editIndex = layedit.build("fwb");

                        }else{
                            layer.alert("该产品不存在");
                        }
                    });
            }
        });
    }
);


<!DOCTYPE html>
<html>
<head>
    <meta charset="=utf-8">
    <title>这个叫title，也就是网页的名字，在head里面，前面有一个meta, #meta charset="=utf-8"# </title>
</head>
<body>
<div id="MyQrcode"></div>
<div id="orderId">${orderId}</div>
<div id="returnUrl">${returnUrl}</div>
<script src="https://cdn.bootcss.com/jquery/1.5.1/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/jquery.qrcode/1.0/jquery.qrcode.min.js"></script>
<script>
    jQuery('#MyQrcode').qrcode({
        text	: "${codeUrl}"
    });

    $(function () {
        //定时器，两秒查询一次
        setInterval(function () {
            console.log("开始查询支付状态")
            $.ajax({
                url:'/pay/quereByOrderId',
                data:{
                    'orderId':$('#orderId').text()
                },
                success: function(result) {
                    console.log(result)
                    if(result.platformStatus != null &&
                        result.platformStatus === 'SUCCESS') {
                        location.href = $('#returnUrl').text()
                    }
                },
                error: function(result) {
                    alert(result)
                }
            })
        }, 2000)
    });
</script>
</body>

</html>

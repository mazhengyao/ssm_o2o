$(function(){
    //获取店铺信息 渲染出来就可以了
    getlist();
    function getlist(e){
        $.ajax({
            url:'/o2o/shopadmin/getshoplist',
            type:'get',
            dataType:"json",
            success:function(data){
                if(data.success){
                    handleList(data.shopList);
                    handleUser(data.user);
                }
            }
        });
 
        //显示用户名
        function handleUser(data){
            $('#user-name').text(data.name);
        }
 
        //显示传回来的用户名下的店铺列表
        function handleList(data){
            var html = '';
            data.map(function(item, index){
                html += '<div class="row row-shop"><div class="col-40">'
                    + item.shopName +'</div><div class="col-40">'+
                    shopStatus(item.enableStatus) +'</div><div class="col-20">'
                    + goShop(item.enableStatus, item.shopId) + '</div></div>';
            });
            $('.shop-wrap').html(html);
        }
 
        function shopStatus(data){
            if(data == 0){
                return '审核中';
            } else if(data == -1){
                return '店铺非法';
            } else if(data == 1){
                return '审核通过';
            }
        }
 
        //进入店铺管理页面，一个超链接
        function goShop(status, id){
            if(status == 1){
                return '<a href="/o2o/shopadmin/shopmanagement?shopId=' + id + '">进入</a>';
            } else{
                return '';
            }
        }
    }
});

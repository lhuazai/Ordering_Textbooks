/**
 * Created by Administrator on 2017/5/6.
 */
function showLoadingLayer() {
    if($('.loading-layer').length!=0){
        return
    }
    $('body').append('<div class="loading-layer">' +
        '<div class="shelter" style=""></div>'+
        ' <div class="double-bounce1"></div> <div class="double-bounce2"></div></div>')
}

function  removeLoadingLayer() {
    $('.loading-layer').remove()
}

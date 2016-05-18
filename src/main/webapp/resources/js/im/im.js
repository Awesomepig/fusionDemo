/**
 * Created by eric on 16-5-17.
 */
var emojiCodes = ["1f60a","1f60b","1f60c","1f60d","1f60e","1f60f",
                    "1f61a","1f61b","1f61c","1f61d","1f61e","1f61f",
                    "1f62a","1f62b","1f62c","1f62d","1f62e","1f62f",
                    "1f63a","1f63b","1f63c","1f63d","1f63e","1f63f"];
var emojiTitle = ["1f60a","1f60b","1f60c","1f60d","1f60e","1f60f",
                    "1f61a","1f61b","1f61c","1f61d","1f61e","1f61f",
                    "1f62a","1f62b","1f62c","1f62d","1f62e","1f62f",
                    "1f63a","1f63b","1f63c","1f63d","1f63e","1f63f"];

$(document).ready(function(){

    var ediv = $("#divE_left");
    // 循环数组,添加emoji
    for(var i = 0;i<emojiCodes.length;i++){
        var code = emojiCodes[i];
        var title = emojiTitle[i];
        var sub = " | ";
        if ((i+1)%5==0){
            sub = " <br/> ";
        }
        ediv.append("<input type='radio' id='emoji_"+code+"' name='emoji_radio' value='"+code+"'>" +
            "<img src='"+contextPath+"/resources/emoji/"+code+".png'  alt='"+title+"' title='"+title+"'>"+sub);

    }
});

function preEmoji(){
    // 1.获取radio值并赋值
    $("#mesE").val($('input[type="radio"][name="emoji_radio"]:checked').val());
    // 2.提交表单
    $("#form_emoji").submit();
}
//제목, 작성자, 내용 빈칸 확인
$(document).ready(function () {
        $("#inputTitle").blur(function () {
            var title = $("#inputTitle").val();
            if(title ==""){
                $("#inputTitle_check").text("필수정보입니다.");
                $("#inputTitle_check").css("color","red");
            }
            else
                $("#inputTitle_check").text("");
        })

        $("#inputWriter").blur(function () {
            var writer = $("#inputWriter").val();
            if(writer ==""){
                $("#inputWriter_check").text("필수정보입니다.");
                $("#inputWriter_check").css("color","red");
            }
            else
                $("#inputWriter_check").text("");
        })

        $("#inputContent").blur(function () {
            var content = $("#inputContent").val();
            if(content ==""){
                $("#inputContent_check").text("필수정보입니다.");
                $("#inputContent_check").css("color","red");
            }
            else
                $("#inputContent_check").text("");
        })

    }
)

function checkValue() {
    var titleFlag = false;
    var writerFlag = false;
    var contentFlag = false;

    //아이디 빈칸 확인
    var title = $("#inputTitle").val();
    if(title ==""){
        $("#inputTitle_check").text("필수정보입니다.");
        $("#inputTitle_check").css("color","red");
    }
    else{
        titleFlag = true;
        $("#inputTitle_check").text("");
    }

    //작성자 빈칸 확인
    var writer = $("#inputWriter").val();
    if(writer ==""){
        $("#inputWriter_check").text("필수정보입니다.");
        $("#inputWriter_check").css("color","red");
    }
    else{
        writerFlag = true;
        $("#inputWriter_check").text("");
    }

    //내용 빈칸 확인
    var content = $("#inputContent").val();
    if(content ==""){
        $("#inputContent_check").text("필수정보입니다.");
        $("#inputContent_check").css("color","red");
    }
    else{
        contentFlag = true;
        $("#inputContent_check").text("");
    }

    return titleFlag && writerFlag && contentFlag;
}
/**
 * ID : 'suneditor_sample'
 * ClassName : 'sun-eidtor'
 */
    // ID or DOM object
const editor = SUNEDITOR.create((document.getElementById('inputContent') || 'inputContent'),{
        // All of the plugins are loaded in the "window.SUNEDITOR" object in dist/suneditor.min.js file
        // Insert options
        // Language global object (default: en)
        lang: SUNEDITOR_LANG['ko']
    });
document.getElementById('suneditor_inputContent').style.cssText = "width: auto";

//제목, 작성자, 내용 빈칸 확인
function checkValue__() {
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
    //여기서 rich editor의 전체 html코드를 가져와야 한다.
    const content = editor.getContents(true)
    if(content == '<p><br></p>'){
        $("#inputContent_check").text("필수정보입니다.");
        $("#inputContent_check").css("color","red");
    }
    else{
        contentFlag = true;
        $("#inputContent_check").text("");
        $("#inputContent").text(content);   //rich editor의 내용을 inputContent테그의 text에 적용해서 form 전송 가능하게 함
    }
    return titleFlag && writerFlag && contentFlag;
}
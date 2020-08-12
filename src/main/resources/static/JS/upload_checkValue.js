function checkValue(){
    var uploadData = document.getElementById("ex_filename").value;
    var videoName = document.getElementById("videoname").value;
    if(uploadData == ""){
        alert("업로드 할 동영상을 선택해 주세요");
        return;
    }
    if(videoName == ""){
        alert("업로드 할 동영상 제목을 입력해 주세요.");
    }
}

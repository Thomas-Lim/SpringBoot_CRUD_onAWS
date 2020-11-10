// index.js 만의 유효범위 (scope)를 만들어 사용.
// 방법은 var index라는 객체를 만들어 해당 객체에서 필요한 모든 function을 선언하는 것임.
// 이렇게 하면 index 객체 안에서만 function이 유효하기 때문에 다른 JS와 겹칠 위험이 사라진다.

var main = {
    init : function () {
        var _this = this;
        $('#btn-save').on('click', function () { // 등록 버튼의 id="btn-save"명
            _this.save();
        });
    },
    save : function () {
        var data = {
            title: $('#title').val(), // 각 작성 폼의 id 명 매칭
            author: $('#author').val(),
            content: $('#content').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/posts',  // h2 DB에 저장되는 api 기능.
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 등록되었습니다.');
            window.location.href = '/';  // 글 등록이 성공하면 메인페이지로 이동함.
        }).fail(function (error) {
                    alert(JSON.stringify(error));
        });
    }

};

main.init();

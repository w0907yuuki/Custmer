// テスト(GET通信)ボタンクリック時のイベント
$(function(){
	const changedData = [
		    {
		        "name": "John",
		        "email": "john@example.com"
		    },
		    {
		        "name": "Jane",
		        "email": "jane@example.com"
		    }
			];
      $("#button-get").click(
          function () {
              $.ajax({
                  // 渡したいデータをurlのクエリパラメータで指定
                  url: "https://httpbin.org/get?param1=p1&param2=p2",
                  type: "GET",
                  dataType: "json"
              })
                  .done(function (data) {
                      // 正常レスポンス
                      console.log(data);
					 
					  
                  })
                  .fail(function () {
                      console.log("通信エラー");
                  });
          }
      );
  
      // テスト(POST通信)ボタンクリック時のイベント
      $("#button-post").click(
          function () {
              $.ajax({
                  //url: "https://httpbin.org/post",
				  url:"/test",
                  type: "POST",
                  dataType: "json",
                  // 渡したいデータを指定
                  data: changedData
              })
                  .done(function (data) {
                      // 正常レスポンス
                      console.log(data);
					  console.log(changedData);
                  })
                  .fail(function () {
                      console.log("通信エラー");
                  });
          }
      );
	  });
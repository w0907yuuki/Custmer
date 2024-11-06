function populateDates() {
	const today = new Date();
	       const year = today.getFullYear();
	       const month = (today.getMonth() + 1).toString().padStart(2, '0');
	       const day = today.getDate().toString().padStart(2, '0');
	       const todayDate = `${year}-${month}-${day}`;

	       // 初期設定で1年間分の範囲を設定
	       const minDate = `${year - 1}-${month}-${day}`;
	       const maxDate = `${year + 1}-${month}-${day}`;

	       // input要素に設定
	       const calendar = document.getElementById('calendar');
	       calendar.value = todayDate;
	       calendar.min = minDate;
	       calendar.max = maxDate;
           }
       

       // ページロード時に日付をセレクトボックスに設定
       window.onload = populateDates;
	   
   $(document).ready(function() {
              $('#receptDummyBtn').on('click', function() {
                  // 入力値を取得
                  const date = $('#calendar').val();
                  const headcount = $('#headcount option:selected').text();
                  const course = $('#course option:selected').text();

                  // モーダル内の要素に設定
                  $('#confirmDate').text(date || "未選択");
                  $('#confirmHeadcount').text(headcount || "未選択");
                  $('#confirmCourse').text(course || "未選択");
              });
	});

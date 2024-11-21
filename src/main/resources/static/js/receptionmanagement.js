$(function() {
	
    // テーブルの行をクリックしたときの処理
    $('#receptionList tbody tr').on('click', function() {
        // すべての行の選択状態を解除
        $('#receptionList tbody tr').removeClass('table-row-active');
        // クリックされた行に選択状態のクラスを追加
        $(this).addClass('table-row-active');
        // 更新ボタン、削除ボタンを活性化
        //$('#editBtn').removeAttr('disabled');
        $('#deleteDummyBtn').removeAttr('disabled');
        // ログインID一時保管
        editSelectedreceptionid($(this));
		
    });
    
    $('#deleteOkBtn').click(function() {
        $('#deleteBtn').trigger('click');
    });
	 // 編集ボタンがクリックされた時の処理
	    $('#editBtn').click(function() {
			$('#saveBtn').removeAttr('disabled');
			 $('#receptionList tbody tr').each(function() {
				      $(this).find('td').each(function(index) {
				            // `ID`列（最初の列）は編集不可
				            if (index === 0) {  // `index === 0` は最初の列（ID列）
				                return;  // この列は何もしないでスキップ
				            }

				            var currentText = $(this).text();  // 現在のテキストを取得
				            var newInput = $('<input>', {
				                type: 'text',
				                value: currentText  // 初期値として現在のテキストを設定
				            });

				            // 現在の td を新しい input 要素に置き換える
				            $(this).empty().append(newInput);  // <td> の中身を空にして、新しい input を挿入
				            newInput.focus();  // フォーカスを input に設定
				        });
				    });
				});
				
		$(".editable").on("input", function() {
			$(this).attr("data-changed", "true");
		});
		
		$("#saveBtn").click(function() {
			saveChanges();
		});
	});

/**
 * テーブルで選択された行のログインIDを画面のhidden要素に保管します。
 * 
 * @param row 選択された行情報
 */
function editSelectedreceptionid(row) {
    row.find('td').each(function() {
        var columnId = $(this).attr('id');
        if (columnId.startsWith('id_')) {
            $('#selectedreceptionid').val($(this).text());
            //console.log($('#selectedreceptionid').val());
             
            return false;
        }
    });
}

//編集した分だけをサーバ側に送りたい場合Ajaxを使用しなければならない

function saveChanges() {
	const changedData = collectChangedData();
		
	console.log(JSON.stringify(changedData));
	/*
	console.log("Keys in changedData:", Object.keys(changedData));
	console.log("First element in changedData:", changedData[0]);
	console.log("changedData length:", changedData.length);
	console.log("Is changedData an array?", Array.isArray(changedData));
	console.log("Type of changedData:", typeof changedData);*/
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	/*$(document).ajaxSend(function(e, xhr, options) {
		xhr.setRequestHeader(header, token);
	});*/
	console.log("CSRF Token:", token);
	console.log("CSRF Header:",header);
	$.ajax({
	    url: "/Reception/update",
	    type: "POST",
	    contentType: "application/json",  // 正しく設定されています
	    dataType: "json",
	    data: JSON.stringify(changedData),  // 送信するデータをJSON形式で文字列化
		beforeSend: function(xhr) {
		       xhr.setRequestHeader(header, token);},
	})
	.done(function (data) {
	    console.log(data);
	})
	.fail(function (jqXHR, textStatus, errorThrown) {
	    console.log("通信エラー: " + textStatus + ", " + errorThrown);
	});
}


function collectChangedData() {
    const changedData = [];
    $('#receptionList tbody tr.table-row-active td').each(function () {
        const inputField = $(this).find('input.editable');

        if (inputField.length > 0) {
            const originalValue = inputField.attr('data-original-value');
            const currentValue = inputField.val();

            if (originalValue !== currentValue) {
                const fieldId = $(this).attr('id'); // 各列のIDからフィールド情報を取得
                changedData.push({
                    fieldId: fieldId,
                    value: currentValue,
                });
            }
        }
    });
    return changedData;
}

function resetChangedFlags() {
    $("[data-changed='true']").removeAttr("data-changed");
}


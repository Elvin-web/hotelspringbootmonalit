function addExpensesCategory() {
    var name = $('#text-input').val();
    var data = {
        name: name
    };
    $.ajax({
        url: 'addExpensesType',
        type: 'POST',
        dataType: 'text',
        data: data,
        success: function (response) {
            if (response == 'success') {
                alert('Expenses type has been successfully added');
                window.location.href='/hotelMonalit/getExpensesTypeList';
            } else {
                alert('Problem! Expenses type has not been successfully added!');
            }
        },
        error: function () {
            alert('Error');
        }
    });
}

function updateExpensesCategory(expensesCategoryId) {
    var name = $('#text-inputU').val();
    var data = {
        name: name,
        expensesCategoryId: expensesCategoryId
    };
    $.ajax({
        url: 'updateExpensesType',
        type: 'POST',
        dataType: 'text',
        data: data,
        success: function (response) {
            if (response == 'success') {
                //  $('#successMessage').load('views/successMessage.jsp').dialog('open');
                alert('Expenses type has been successfully updated');
                window.location.href='/hotelMonalit/getExpensesTypeList';
            } else {
                alert('Problem! Expenses type has not been successfully updated!');
                // $('#errorMessage').load('views/errorMessage.jsp').dialog('open');
            }
        },
        error: function () {
            alert('Error');
        }
    });
}
function deleteExpensesCategory(expensesCategoryId, expensesType) {
    var isDelete = confirm("Are you sure to delete " + expensesType + " ?");
    if (isDelete) {
        $.ajax({
            url: 'deleteExpensesType',
            type: 'POST',
            data: 'expensesCategoryId=' + expensesCategoryId,
            dataType: 'text',
            success: function (response) {
                if (response = 'success') {
                    alert('Expenses type has been successfully deleted!');
                    window.location.href='/hotelMonalit/getExpensesTypeList';
                } else {
                    alert('Problem! Expenses type has not been successfully deleted!');
                }
            }
        });
    }
}

$(function () {
    // $("[data-toggle=tooltip]").tooltip();
    $('#closeId').click(function () {
        $('#successMessage').dialog("close");
    });
    $('#closeIdE').click(function () {
        $('#errorMessage').dialog("close");
    });
    $('#expensesCategorySubmitId').click(function () {
        addExpensesCategory();
    });

    $('#expensesCategorySubmitIdU').click(function () {
        updateExpensesCategory(expensesCategoryId);
    });

});

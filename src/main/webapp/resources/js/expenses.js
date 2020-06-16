

function updateExpenses(expensesId) {
    var dataInsert = $('#text-inputU').val();
    var name = $('#text-input1U').val();
    var expensesType = $('#selectU').val();
    var amount = $('#text-input2U').val();
    var attachment = $('#file-inputU').val();
    var data = {
        dataInsert: dataInsert,
        name: name,
        expensesType: expensesType,
        amount: amount,
        attachment: attachment,
        expensesId: expensesId
    };
    $.ajax({
        url: 'es?action=updateExpenses',
        type: 'POST',
        dataType: 'text',
        data: data,
        success: function (response) {
            if (response == 'success') {
                alert('Expenses  has been successfully updated');
                window.location.href='/hotel/es?action=getExpensesList';
            } else {
                alert('Problem! Expenses  has not been successfully updated!');
            }
        },
        error: function () {
            alert('Error');
        }
    });
}
function deleteExpenses(expensesId,name) {
    var isDelete = confirm("Are you sure to delete " + name + " ?");
    if (isDelete) {
        $.ajax({
            url: 'deleteExpenses',
            type: 'POST',
            data: 'expensesId=' + expensesId,
            dataType: 'text',
            success: function (response) {
                if (response = 'success') {
                    alert('Expenses  has been successfully deleted!');
                    window.location.href='/hotelMonalit/getExpensesList';
                } else {
                    alert('Problem! Expenses  has not been successfully deleted!');
                }
            }
        });
    }
}
$(function () {
    $('#expensesSubmitIdU').click(function () {
        updateExpenses(expensesId);
    });

});
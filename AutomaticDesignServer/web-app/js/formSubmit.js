/*
 * Some javascript to submit the specified form with the specified action
 */

function submitForm(formName, action) {
    $('form[name="'+formName+'"]').attr('action', action);
    $('form[name="'+formName+'"]').submit();
}


function confirmSubmitForm(formName, action, message) {
    
    var shouldContinue = confirm(message);
    
    if ( shouldContinue ) {
        submitForm(formName, action);
    }
}


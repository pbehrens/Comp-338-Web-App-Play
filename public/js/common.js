var userAllowance = {guest: true, member: false, staff: false};

window.onload = function(e){
  if($.cookies.get('user')){
    userAllowance = $.cookies.get('user');
  }
  if(userAllowance.guest){
	  $('#guestToggle').button('toggle');
  }
  if(userAllowance.member){
	  $('#memberToggle').button('toggle');
  }
  if(userAllowance.staff){
	  $('#staffToggle').button('toggle');
  }
  $('#guestToggle').on('click', function() {
	  userAllowance.guest = !userAllowance.guest
  })
  $('#memberToggle').on('click', function() {
	  userAllowance.member = !userAllowance.member
  })
  $('#staffToggle').on('click', function() {
	  userAllowance.staff = !userAllowance.staff
  })
}

$('#uacModal').on('show', function() {
})

function checkOutAvailable(){
  alert('Checked Out!');
  $("#goggle").replaceWith('<span id="goggle" class="label label-important" onclick="checkOutAvailable()">Unavailable</span>');
}

function checkOutUnavailable(){
  alert("You're next in line!");
}

function storeUserClasses(){
  $.cookies.set('user', userAllowance);
  $('#uacModal').modal('hide');
}

function sendUACModal(){
  $('#uacModal').modal('show');
}

function goToGuest(){
  if(userAllowance.guest){
    window.location.replace("./guest.html");
  }
  else{
    sendUACModal();
  }
}

function goToMember(){
  if(userAllowance.member){
    window.location.replace("./member.html");
  }
  else{
    sendUACModal();
  }
}

function goToStaff(){
  if(userAllowance.staff){
    window.location.replace("./staff.html");
  }
  else{
    sendUACModal();
  }
}
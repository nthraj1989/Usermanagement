   
    $(function(){
      	$("#countryId").change(function(event){
      		$('#stateId').find('option').remove();
      		$('#stateId').append($('<option></option>').val('')
      				.html('--select--'));
      		$('#cityId').find('option').remove();
      		$('#cityId').append($('<option></option>').val('').html('--select--'));
      		var countryid=$('#countryId').val();
      		$.ajax({
      			type:"GET",
      			url:"/getstates?cid="+countryid,
      			async: false,
      			success : function(response){
      				$.each(response,function(key,value){
      					$('#stateId').append(
      			                $('<option></option>').val(key).html(value)
      			            );
      				})
      			}
      		 })
      	  })
      });
      	

    $(function(){
      	$("#stateId").change(function(event){
      		$('#cityId').find('option').remove();
      		$('#cityId').append($('<option></option>').val('').html('--select--'));
      		var stateId=$('#stateId').val();
      		$.ajax({
      			type:"GET",
      			url:"/getcities?sid="+stateId,
      			async: false,
      			success : function(response){
      				$.each(response,function(key,value){
      					$('#cityId').append($('<option></option>').val(key).html(value));
      				})
      			}	 
      		 })
      	  })
      });

    
    
   
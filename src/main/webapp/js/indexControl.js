 $(function(){
     search();
 })

// 삭제
function delinfo(info){
  if(confirm("삭제하시겠습니까?")){
    if(info.length>1){ // 다건 
      axios.put('/rest/student?action=delete',info)
        .then(function(response){
          alert("삭제되었습니다.")
          search();
        })
        .catch(function(error){
        })
    }else{ // 단건
      axios.delete('/rest/student/'+info[0].id)
        .then(function(response){
          alert("삭제되었습니다.")
          search()
        })
        .catch(function(error){
        })
    }
  }
}
 // 조회
 function search(info){
   if(info){
    axios.get('/rest/student',{
      params:{
        studentName : info.ipt_student_name
      }
    })
    .then(function(response){
      grid.resetData(response.data.resultList);
    })
    .catch(function(){
    }) 
   }else{
    axios.get('/rest/student')
    .then(function(response){
      grid.resetData(response.data.resultList);
    })
    .catch(function(){
    }) 
   }
   
 }

 // 저장
 function save(info){
   axios.post('/rest/student',info)
     .then(function(response){
        alert("저장되었습니다.")
        search();
     })
     .catch(function(){
     })
 }


/*--------- 버튼 클릭 -----------*/

// 조회 버튼 클릭
$('#btnSearch').click(function(){
  search();
})

// 행추가 버튼 클릭 
$('#btnAddRow').click(function(){
  grid.appendRow();
  grid.focus(grid.getRowCount()-1,'studentName',true)
})

// 저장 버튼 클릭
$('#btnSave').click(function(){
  var info = grid.getModifiedRows();
  if(checkinfo(info.createdRows) || checkinfo(info.updatedRows)){
    alert("정보를 모두 입력하세요");
    return;
  }
  save(info);
})

// 삭제 버튼 클릭
$('#btnDel').click(function(){
  var info = grid.getCheckedRows();
  
  info = delemptyrow(info)
  if(info != ''){
    delinfo(info);
  }
})

// 검색 버튼 클릭
$('#btnLook').click(function(){
  var obj = null;
  var arr = $('#frmStudentInfo').serializeArray();
  if(arr){
    obj = {};
    jQuery.each(arr, function(){
      obj[this.name] = this.value;
    });
  }
  search(obj)
})


/*---------- 예외 처리 -------------*/
function checkinfo(info){
  var chk = false; 
  for(var i=0; i<info.length; i++){
    if(info[0].studentName =='' || info[0].email =='' || info[0].age=='' || info[0].studentRole=='' || info[0].visitCnt==''){
      chk = true;
    }
  }
  return chk
}

function delemptyrow(info){
  if(info == ''){
    alert("삭제할 데이터를 선택하세요");
    return;
  }
  for(var i=0;i<info.length;i++){
    if(info[i].id == ''){
      grid.removeRow(info[i].rowKey);
    }
  }
  info = grid.getCheckedRows();
  return info
}
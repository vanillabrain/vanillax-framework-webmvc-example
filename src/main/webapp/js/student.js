var Grid = tui.Grid;
// Grid Theme 정의
Grid.applyTheme('striped',{
  grid: {
    background: '#fff',
    border: '#ccc',
    text: '#444'
  },
  selection: {
    background: '#4daaf9',
    border: '#004082'
  },
  toolbar: {
    border: '#ccc',
    background: '#fff'
  },
  scrollbar: {
    background: '#f5f5f5',
    thumb: '#d9d9d9',
    active: '#c1c1c1'
  },
  row: {
    even: {
      background: '#f3ffe3'
    },
    hover: {
      background: '#ccc'
    }
  },
  cell: {
    normal: {
      background: '#fbfbfb',
      border: '#e0e0e0'
    },
    head: {
      background: '#eee',
      border: '#ccc'
    },
    editable: {
      background: '#fbfbfb'
    },
    selectedHead: {
      background: '#d8d8d8'
    },
    focused: {
      border: '#418ed4'
    },
    disabled: {
      text: '#b0b0b0'
    }
  }
})

// Grid 정의
const grid = new Grid({
    el: document.getElementById('grid'),
    rowHeaders: ['checkbox'],
    scrollX: false,
    scrollY: false,
    columns: [
      {
        header: 'ID',
        name: 'id',
        align: 'center',
        width:'100'
//        ,hidden: true
      },
      {
        header: 'StudentName',
        name: 'studentName',
        resizable: false,
        align: 'center',
        editor:{
          type:'text',
          option:'useViewMode'
        }
      },
      {
        header: 'E-Mail',
        name: 'email',
        align: 'center',
        editor:{
          type:'text',
          option:''
        }
      },
      {
        header: 'Age',
        name: 'age',
        align: 'center',
        sortable:true,
        editor:{
          type:'text',
          option:''
        }
      },
      {
        header: 'StudentRole',
        name: 'studentRole',
        align: 'center',
        formatter:'listItemText',
        editor:{
          type:'select',
          options: {
						listItems: [
							{ text: 'Leader', value: 'LEADR' },
							{ text: 'Normal', value: 'NORML' }
						]
          }
        }
      },
      {
        header: 'VisitCount',
        name: 'visitCnt',
        align: 'center',
        editor:{
          type:'text',
          option:''
        }
      },
      {
        header: 'RegDate',
        name: 'regDate',
        align: 'center',
      }
    ],
    columnOptions: {
      resizable:true
    }
  });


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
    let token = vanillaxHelper.getToken();
    if(info){
        axios
            .get('/rest/student',{
                headers: {'X-Token': token},
                params:{
                    studentName : info.ipt_student_name
                }
            })
            .then(function(response){
                grid.resetData(response.data.resultList);
            })
            .catch(function(err){
                //error
            });
    }else{
        axios.get('/rest/student',{
                headers: {'X-Token': token}
            })
            .then(function(response){
                grid.resetData(response.data.resultList);
            })
            .catch(function(err){
                //error
            });
    }
   
 }

 // 저장
function save(info){
    let token = vanillaxHelper.getToken();
    axios.post('/rest/student',info,{
        headers: {'X-Token': token},
    })
    .then(function(response){
        alert("저장되었습니다.")
        search();
    })
    .catch(function(err){
        //error
    });
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
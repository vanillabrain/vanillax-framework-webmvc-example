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
<template>
  <div>
    <el-table :data="tableData" style="width: 100%">
    <el-table-column prop="date" label="Symbol" width="180" />
    <el-table-column prop="name" label="Full Exchange Name" width="180" />
    <el-table-column prop="name" label="Price(Now)" width="180" />
    <el-table-column prop="name" label="Market Change" width="180" />
    <el-table-column prop="name" label="Percent Change" width="180" />
    <el-table-column prop="name" label="Market Cap" width="180" />
    <el-table-column prop="name" label="Volume" width="180" />
  </el-table>
  </div>
</template>

<script setup lang="ts">

const tableData = [
  {
    date: '2016-05-03',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles',
  },
  {
    date: '2016-05-02',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles',
  },
  {
    date: '2016-05-04',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles',
  },
  {
    date: '2016-05-01',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles',
  },
]


</script>
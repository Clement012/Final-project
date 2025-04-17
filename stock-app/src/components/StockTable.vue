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
  <p>{{ formattedDate }}</p>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue';
import { onMounted, onBeforeUnmount } from 'vue';

let interval: number;

onMounted(() => {
  interval = setInterval(() => {
    currentDate.value = new Date();
    formattedDate.value = formatDate(currentDate.value);
  }, 1000);
});

onBeforeUnmount(() => {
  clearInterval(interval);
});
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

const currentDate = ref(new Date())
const formattedDate = ref(formatDate(currentDate.value));
// Date
function formatDate(date: Date): string {
  const year = date.getFullYear();
  const month = String(date.getMonth() + 1).padStart(2, '0'); // Months are zero-based
  const day = String(date.getDate()).padStart(2, '0');
  const hours = String(date.getHours()).padStart(2, '0');
  const minutes = String(date.getMinutes()).padStart(2, '0');
  const seconds = String(date.getSeconds()).padStart(2, '0');

  return `${year}/${month}/${day} ${hours}:${minutes}:${seconds}`;
}

</script>
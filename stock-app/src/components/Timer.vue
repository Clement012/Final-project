<template>
  <div>
    <div class="flex justify-end"> 
      <p>{{ formattedDate }}</p>
    </div>
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
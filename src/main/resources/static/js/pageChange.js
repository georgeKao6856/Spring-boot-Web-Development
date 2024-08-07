const rowsPerPage = 5;
let currentPage = 1;
const table = document.getElementById('userTable');
const tbody = table.getElementsByTagName('tbody')[0];
const rows = tbody.getElementsByTagName('tr');
const totalPages = Math.ceil(rows.length / rowsPerPage);

function showPage(page) {
    const start = (page - 1) * rowsPerPage;
    const end = start + rowsPerPage;

    for (let i = 0; i < rows.length; i++) {
        if (i >= start && i < end) {
            rows[i].style.display = '';
        } else {
            rows[i].style.display = 'none';
        }
    }

    const showPages = "Page " + currentPage +  "of " + totalPages;
    document.getElementById('pageInfo').innerText = showPages;
}

function prevPage() {
    if (currentPage > 1) {
        currentPage--;
        showPage(currentPage);
    }
}

function nextPage() {
    if (currentPage < totalPages) {
        currentPage++;
        showPage(currentPage);
    }
}

// Initialize the table to show the first page
showPage(currentPage);

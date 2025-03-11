// 삭제 기능
const deleteButton = document.getElementById('delete-btn');

if(deleteButton){
    deleteButton.addEventListener('click', event => {
        let id = document.getElementById('animation-id').value;
        fetch(`/api/animations/${id}`,{
            method: 'DELETE'
        })
            .then(() => {
                alert('삭제가 완료되었습니다.');
                location.replace('/animations');
        });
    });
}

// 수정 기능
const modifyButton = document.getElementById('modify-btn');

if (modifyButton) {
    modifyButton.addEventListener('click', event => {
        let params = new URLSearchParams(location.search);
        let id = params.get('id');


        fetch(`/api/animations/${id}`,{
            method: 'PUT',
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                title: document.getElementById('title').value,
                description: document.getElementById('description').value,
                thumbnail: document.getElementById('thumbnail').value
            })
        })
            .then(() => {
                alert('수정이 완료되었습니다.');
                location.replace(`/animations/${id}`);
            });
    });
}

// 등록 기능
const createButton = document.getElementById("create-btn");

if(createButton){
    createButton.addEventListener("click", (evert) => {
        fetch("/api/animations", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                title: document.getElementById("title").value,
                description: document.getElementById("description").value,
                thumbnail: document.getElementById("thumbnail").value
            }),
        }).then(() => {
            alert("등록이 완료되었습니다.");
            location.replace("/animations");
        });
    });
}
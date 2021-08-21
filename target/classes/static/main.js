document.getElementById('photo-upload').addEventListener('change',uploadPhoto);
function uploadPhoto(event) {{
    let file = event.target.files[0];
    const formData = new FormData();
    formData.append('file',file);
    fetch("http://localhost:8080/api//photo/1/comptes",{
        method: 'POST',
        body: formData
    }).then(data => {
        document.getElementById("compte-photo").src = data.url+'?t='+new Date().getTime();
    });
}
    
}
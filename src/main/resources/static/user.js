$(async function() {
    await thisUser();
});
async function thisUser() {
    fetch("http://localhost:8080/api/userpanel")
        .then(response => response.json())
        .then(data => {
            // Добавляем информацию в шапку
            $('#headerUsername').append(data.username);
            let roles = data
            .checkedRoles
            .map(role => " " + role.name.substring(5));
            $('#headerRoles').append(roles);

            //Добавляем информацию в таблицу
            let user = `$(
            <tr>
                <td>${data.id}</td>
                <td>${data.username}</td>
                <td>${data.surname}</td>
                <td>${data.age}</td>
                <td>${data.email}</td>
                <td>${roles.checkedRoles}</td>)`;
            $('#userAllInfo').append(user);
        })
}
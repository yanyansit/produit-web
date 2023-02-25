
fetch('api')
    .then(res => res.json())
    .then(res => {
        var produitListNode = document.getElementById('produit-list');
        produitListNode.innerHTML = "";

        var table = document.createElement("table");
        table.setAttribute("border","1");
        produitListNode.appendChild(table);

        res.forEach(produit => {

            var tr = document.createElement("tr");
            table.appendChild(tr);

            var td = document.createElement("td");
            var text = document.createTextNode(`${produit.idProduit}`);
            td.appendChild(text);
            tr.appendChild(td);

            td = document.createElement("td");
            text = document.createTextNode(`${produit.nomProduit}`);
            td.appendChild(text);
            tr.appendChild(td);

            td = document.createElement("td");
            var button = document.createElement("button");
            button.setAttribute("type","button");
            button.onclick = function() {
                showDetail(`${produit.idProduit}`);
            };
            text = document.createTextNode("Details");
            button.appendChild(text);
            td.appendChild(button);
            tr.appendChild(td);
        });
    });


function showDetail(id){
    fetch("api/"+id)
    .then(res => res.json())
    .then(res => {
        var produitDetailNode = document.getElementById('produit-detail');
        produitDetailNode.innerHTML = "";


        var p = document.createElement("p");
        var text = document.createTextNode(`Number: ${res.idProduit}`);
        p.appendChild(text);
        produitDetailNode.appendChild(p);

        p = document.createElement("p");
        text = document.createTextNode(`Name: ${res.nomProduit}`);
        p.appendChild(text);
        produitDetailNode.appendChild(p);

        p = document.createElement("p");
        text = document.createTextNode(`Prix: ${res.prixProduit}`);
        p.appendChild(text);
        produitDetailNode.appendChild(p);

        p = document.createElement("p");
        text = document.createTextNode(`Date Created: ${res.dateCreation}`);
        p.appendChild(text);
        produitDetailNode.appendChild(p);

    });
}

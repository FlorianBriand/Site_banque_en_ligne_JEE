function generateTBody(columns, data) {
    let headContent = columns.map(column => {
        let th = document.createElement("th");
        th.scope = "col";
        th.innerText = column;

        return th;
    });

    let children = data.map(element => {
        let tr = document.createElement("tr");

        element.map(value => {
            let td = document.createElement("td");
            td.innerText = value;
            tr.appendChild(td);
        })
        return tr;
    });

    return {
        headContent,
        children,
    }
}

let tableau = document.getElementById("table-total-content");
let head = document.getElementById("table-total-head");
let titre = document.getElementById("titre-total");

function ApplyGeneratedTBody(data) {
    head.innerHTML = "";
    tableau.innerHTML = "";
    
    data.headContent.map(title => head.appendChild(title));

    data.children.map(content => tableau.appendChild(content));
}


  async function getAll() {
    var response = await fetch("http://localhost:8080/dat3_startcode_war_exploded/api/person/all");
    var data = await response.json();
    let personTable = document.getElementById("searsh")
    const tabelArray = data.map(person=>
        `<tr>
            <td>${person.id}</td>
            <td>${person.firstName}</td>
            <td>${person.lastName}</td>
            <td>${person.phone}</td> 
         </tr>`)
        personTable.innerHTML = tabelArray.join('');
    }
    async function addPerson() {

        let options = {
            method: "POST",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                firstName:   document.getElementById("fname").value,
                lastName: document.getElementById("lname").value,
                phone: document.getElementById("phone").value
            })
        }
        fetch("http://localhost:8080/dat3_startcode_war_exploded/api/person/add",options);

    }


  var persondel=document.getElementById("hint");

         persondel.addEventListener("click",(event)=>{
             event.preventDefault();
             getAll();
         });

  var persondel1=document.getElementById("add");

  persondel1.addEventListener("click",(event)=>{
      event.preventDefault();
      addPerson();
  });

  async function deletePerson() {

      let options = {
          method: "delete",
          headers: {
              'Accept': 'application/json',
              'Content-Type': 'application/json'
          },
          body: JSON.stringify({
              Id:   document.getElementById("num").value

          })
      }
      fetch("http://localhost:8080/dat3_startcode_war_exploded/api/person/",options);

  }

var personDelete= document.getElementById("delete");
  personDelete.addEventListener("click",(event)=>{

      deletePerson();
  })
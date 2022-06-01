let url = 'http://localhost:8081/recauderia/obtenerFrutas'
        fetch(url)
            .then( response => response.json() )
            .then( data => mostrarData(data) )
            .catch( error => console.log(error) )

        const mostrarData = (data) => {
            console.log(data)
            let body = ""
            for (var i = 0; i < data.length; i++) { 
            	let fechaR=new Date(data[i].fechaRegistro)
            	fechaR=agregarCero(fechaR.getDate())+"/"+agregarCero((fechaR.getMonth() + 1))+"/"+fechaR.getFullYear()+" "+agregarCero(fechaR.getHours()) + ":" + agregarCero(fechaR.getMinutes()) + ":" + agregarCero(fechaR.getSeconds())
            	let fechaM=new Date(data[i].fechaModificacion)
            	if (data[i].fechaModificacion ===null) {
            		fechaM=data[i].fechaModificacion

            	}else{
            		fechaM=new Date(data[i].fechaModificacion)
            		fechaM=agregarCero(fechaM.getDate())+"/"+agregarCero((fechaM.getMonth() + 1))+"/"+fechaM.getFullYear()+" "+agregarCero(fechaM.getHours()) + ":" + agregarCero(fechaM.getMinutes()) + ":" + agregarCero(fechaM.getSeconds())}
            	               body+=`<tr><td>${data[i].id}</td><td>${data[i].estatus}</td><td>${data[i].clave}</td><td>${data[i].nombre}</td><td>${data[i].precio}</td><td>${fechaR}</td><td>${fechaM}</td></tr>`
               
            }
            document.querySelector(".data").innerHTML = body
            //console.log(body)
        }
        const agregarCero= (num) =>{
        	let res
        	if (num<10) {
        		res="0"+num
        	}else
        	res=num
        	return res

        }
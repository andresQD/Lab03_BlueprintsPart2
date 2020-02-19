# Lab04_BlueprintsPart2
## Part II

### POST
* Para lograr añadir un nuevo blueprint se uso el formato json de la siguiente manera.

![img1](https://user-images.githubusercontent.com/48091585/74879224-ac2a1580-5336-11ea-99c4-b999cca06078.PNG)

y se ve reflejado asi:

![img3](https://user-images.githubusercontent.com/48091585/74879279-cc59d480-5336-11ea-8ac3-c2f9617c3626.PNG)

### PUT 

* Para el usuario Nicolas actualizamos los puntos de el.


![img2](https://user-images.githubusercontent.com/48091585/74879639-8c472180-5337-11ea-8301-bb1644559b7e.PNG)

y se ve reflejado asi:

![img4](https://user-images.githubusercontent.com/48091585/74879701-b567b200-5337-11ea-98f4-e1f8982b29e9.PNG)

## Part III

### ¿Que condiciones de carrera podrian ocurrir?
Las condiciones de carrera pueden ser:

* Cundo un usuario consulta y al mismo tiempo otro usuario está insertando un nuevo plan. 
* Cuando dos usuarios intentan modificar el mismo plan.
* Cuando dos usuarios intentan agregar el mismo plan.
* Cuando un usuario está consultando y otro está modificando.
* Cuando un usuario desea insertar un nuevo plan y otro quiere actualizarlo
### ¿Cuales son las regiones criticas respectivas?
* La region crítica más fuerte es el HashMap debido a que si hay adición o actualizaciones de planes puede generar conflictos en el hashMap haciendo que ingresen datos erroneos ó vulnerar la integridad de los datos guardados. Otras regiones críticas serian los metodos dee agregar planes y actualizar planes. 
### Posible solución a aplicar:
* Sincronizar los metodos adicionar y actualizar en el caso en el que los dos tengan los mismos parametros. Otra manera más eficiente seria realizar un HashMap concurrente.

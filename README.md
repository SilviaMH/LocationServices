# Gps Android

Aplicación para rastrear la ubicación del usuario y guardarla en un archivo .csv, mediante un *activity*, un *service* y un *intent service*.

## Construcción 🛠️

Se desarrolló la app en Android Studio, tomándose en cuenta los siguiente aspectos:

* Solicitar al usuario permisos para acceder a la ubicación y para escribir en la unidad de almacenamiento externa.
* Utilizar un *Activity* para realizar el rastreo y mostrar un widget para indicar que el rastreo está en proceso y que se está registrando la ubicación el en archivo .csv.
* El widget de carga es una barra de proceso cíclica que está en movimiento mientras se está realizando el rastreo del usuario y se detiene cuando el usuario regresa a la actividad principal. Para ello se guardaron las imágenes de los elementos de widget y el xml de la animación en la carpeta app/res/drawable (loading_0X.png y loading.xml).
* Emplear un *Service* y un *Intent service* para registrar la ubicación del usuario en el archivo .csv. Debido a que los servicos no tienen interfaz gráfica, el proceso del rastreo se lleva a cabo en segundo plano y no se muestra un widget de carga.
* La aplicación también incluye dos EditText para personalizar el intervalo de tiempo y la distancia mínima con la que se actualiza la ubicación.
* Cada vez que el usuario cambia alguno de los parámetros de tiempo y distancia, se debe remover la petición anterior y solicitar una nueva con los métodos *removeUpdates()* y *requestLocationUpdates()* correspondientemente.

## Autor ✒️

**Silvia Martínez H** 


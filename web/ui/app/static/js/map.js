function initAutocomplete() {
          var map = new google.maps.Map(document.getElementById('map'), {
          center: {lat: 50.0, lng: 30.0},
          zoom: 4
        });

        // Create the search box and link it to the UI element.
        var input = document.getElementById('pac-input');
        var add = document.getElementById('add-marker');
        
        var searchBox = new google.maps.places.SearchBox(input);
        map.controls[google.maps.ControlPosition.TOP_LEFT].push(input);

        // Bias the SearchBox results towards current map's viewport.
        map.addListener('bounds_changed', function() {
          searchBox.setBounds(map.getBounds());
        });

        var place;
        var places;
        var flag;
        var existedPlaces = [];
        var placesIdArr = [];
        var markers = [];
        var listPlaces = document.getElementById("enter-place");
        var listArray = document.getElementsByClassName('check');
        var placesToDelete = [];
        var addedValues = [];


        searchBox.addListener('places_changed', function(event) {

          input.addEventListener('onchange', function(){
            event.preventDefault();
          });

          places = searchBox.getPlaces();
         
          if (places.length == 0) {
              return;
          }
          
          if (places.length > 1){
              places.shift();
          }      
          place = places[places.length-1]; 

          document.getElementById('clean-map').addEventListener('click', function(){
                    // Clear out the old markers.
                    if(document )
                    markers.forEach(function(marker) {
                      marker.setMap(null);
                    });
                    markers = [];
                    for (var i = 0; i < listArray.length; i++) {
                      $(listArray[i]).parent('div').remove();
                    }
                    
          });


          // For each place, get the icon, name and location.
          var bounds = new google.maps.LatLngBounds();
          console.log("places: "+places);
          
            if (!place.geometry) {
              console.log("Returned place contains no geometry");
              return;
            }             
            
            // Create a marker for each place.
            if (place.geometry.viewport) {
              // Only geocodes have viewport.
              bounds.union(place.geometry.viewport);
            } else {
              bounds.extend(place.geometry.location);
            }
          
          map.fitBounds(bounds);

          console.log("markers: "+markers);
        });

        add.addEventListener('click', function(){

              for (var i = 0; i < listArray.length; i++) {
                addedValues.push(listArray[i].value);
              }
              console.log("addedValues"+addedValues);
              listPlaces.innerHTML += "<div id=\""+place.place_id+"\"><input type=\"checkbox\" class='check' value=\""+place.name+"\"><label>"+place.name+"</label></div>"; 
                                         
              for (var i = 0; i < listArray.length; i++) {
                if(addedValues[i] == place.name){
                  alert('place already exists!');
                  $('#'+place.place_id).remove();
                  break;
                }else{
                  markers.push(new google.maps.Marker({
                        map: map,
                        title: place.name,
                        placeId: place.place_id,
                        position: place.geometry.location
                  }));
                }console.log(addedValues[i]);
              }
            });

            document.getElementById('delete').addEventListener('click',function(){
              var buf=[];
              //delete checked items from places list
              for (var i = 0; i < listArray.length; i++) {
                if(listArray[i].checked === true){
                    placesToDelete.push(listArray[i]);
                  } 
                } console.log("placesToDelete:"+placesToDelete);
              //delete checked items from added values to check then if there arealready on map
              for (var i = 0; i < placesToDelete.length; i++) {
                for (var j = 0; j < addedValues.length; j++) {
                    if(addedValues[j] == placesToDelete[i].value){
                      addedValues[j] = null;
                    } 
                }
                //remove list item from list
                $(placesToDelete[i]).parent('div').remove();
                //delete marker from map
                console.log("listArray: "+listArray);
                console.log("addedValues: "+addedValues);
                  markers.forEach(function(marker) {
                    if (marker.title == placesToDelete[i].value) {
                      marker.setMap(null);
                    }                     
                  });                  
              }  
            });
}


(function(){
  setTimeout(function(){document.getElementById("pac-input").style.display='block';},2000);
})();
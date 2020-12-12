//
// Skybox using Three.js.
//

var bodyDummy;
var noseDummy;
var wingDummy;
var noseConeDummy;
var tailDummy;
var thruster1Dummy;
var thruster2Dummy;
var thruster3Dummy;
var flame1Dummy;
var flame2Dummy;
var flame3Dummy;
var bunnyDummy;
var centerDummy;

var modelFilename = "models/bunny.obj";
var path = "images/";
////var path = "../images/sky/";
var imageNames = [
                  path + "px.jpg",
                  path + "nx.jpg",
                  path + "py.jpg",
                  path + "ny.jpg",
                  path + "pz.jpg",
                  path + "nz.jpg"
                  ];
				  
var imageFlames = [
                  path + "flames.jpg",
                  path + "flames.jpg",
                  path + "flames.jpg",
                  path + "flames.jpg",
                  path + "flames.jpg",
                  path + "flames.jpg"
                  ];


var axis = 'z';
var paused = false;
var camera;

//translate keypress events to strings
//from http://javascript.info/tutorial/keyboard-events
function getChar(event) {
if (event.which == null) {
 return String.fromCharCode(event.keyCode) // IE
} else if (event.which!=0 && event.charCode!=0) {
 return String.fromCharCode(event.which)   // the rest
} else {
 return null // special key
}
}

function cameraControl(c, ch)
{
  var distance = c.position.length();
  var q, q2;

  switch (ch)
  {
  // camera controls
  case 'w':
    c.translateZ(-0.1);
    return true;
  case 'a':
    c.translateX(-0.1);
    return true;
  case 's':
    c.translateZ(0.1);
    return true;
  case 'd':
    c.translateX(0.1);
    return true;
  case 'r':
    c.translateY(0.1);
    return true;
  case 'f':
    c.translateY(-0.1);
    return true;
  case 'j':
    // need to do extrinsic rotation about world y axis, so multiply camera's quaternion
    // on left
    q = new THREE.Quaternion().setFromAxisAngle(new THREE.Vector3(0, 1, 0),  5 * Math.PI / 180);
    c.applyQuaternion(q);
    return true;
  case 'l':
    q = new THREE.Quaternion().setFromAxisAngle(new THREE.Vector3(0, 1, 0),  -5 * Math.PI / 180);
    c.applyQuaternion(q);
    return true;
  case 'i':
    // intrinsic rotation about camera's x-axis
    c.rotateX(5 * Math.PI / 180);
    return true;
  case 'k':
    c.rotateX(-5 * Math.PI / 180);
    return true;
  case 'O':
    c.lookAt(new THREE.Vector3(0, 0, 0));
    return true;
  case 'S':
    c.fov = Math.min(80, c.fov + 5);
    c.updateProjectionMatrix();
    return true;
  case 'W':
    c.fov = Math.max(5, c.fov  - 5);
    c.updateProjectionMatrix();
    return true;

    // alternates for arrow keys
  case 'J':
    //this.orbitLeft(5, distance)
    c.translateZ(-distance);
    q = new THREE.Quaternion().setFromAxisAngle(new THREE.Vector3(0, 1, 0),  5 * Math.PI / 180);
    c.applyQuaternion(q);
    c.translateZ(distance);
    return true;
  case 'L':
    //this.orbitRight(5, distance)
    c.translateZ(-distance);    
    q = new THREE.Quaternion().setFromAxisAngle(new THREE.Vector3(0, 1, 0),  -5 * Math.PI / 180);
    c.applyQuaternion(q);
    c.translateZ(distance);
    return true;
  case 'I':
    //this.orbitUp(5, distance)
    c.translateZ(-distance);
    c.rotateX(-5 * Math.PI / 180);
    c.translateZ(distance);
    return true;
  case 'K':
    //this.orbitDown(5, distance)
    c.translateZ(-distance);
    c.rotateX(5 * Math.PI / 180);
    c.translateZ(distance);
    return true;
  }
  return false;
}

function handleKeyPress(event)
{
  var ch = getChar(event);
  if (cameraControl(camera, ch)) return;
}

async function start()
{
  window.onkeypress = handleKeyPress;

  var scene = new THREE.Scene();
  camera = new THREE.PerspectiveCamera( 45, 1.5, 0.1, 1000 );
  camera.position.x = 0;
  camera.position.y = 15;
  camera.position.z = 70;
  camera.lookAt(new THREE.Vector3(0, 0, 50));
  camera.rotateX(35 * Math.PI / 180);


  var ourCanvas = document.getElementById('theCanvas');
  var renderer = new THREE.WebGLRenderer({canvas: ourCanvas});

  // load the six images
  var ourCubeMap = new THREE.CubeTextureLoader().load( imageNames );
  
  var bodyCubeMap = new THREE.CubeTextureLoader().load(imageFlames);

  // this is too easy, don't need a mesh or anything
  scene.background = ourCubeMap;
  
  var geometry = await loadOBJPromise(modelFilename);
  var material = new THREE.MeshPhongMaterial( { color: 0x00ff00, specular: 0x222222, shininess: 50} );

  // Note: we can make the PhongMaterial use face normals by selecting FlatShading
  //var material = new THREE.MeshPhongMaterial( { color: 0x00ff00, specular: 0x222222, shininess: 50, shading: THREE.FlatShading} );

  // Create a mesh
  bunnyDummy = new THREE.Mesh( geometry, material );
  
  bunnyDummy.position.set(0,6,0);
  bunnyDummy.scale.set(1,1,1);
  bunnyDummy.rotateY(180 * Math.PI / 180);

  
  geometry = new THREE.CylinderGeometry( 1,1,10,45 );

  // torsoDummy is parent of torso cube and shoulder dummy
  
  centerDummy = new THREE.Object3D();
  centerDummy.position.set(0,10,15);
  bodyDummy = new THREE.Object3D();
  bodyDummy.position.set(0,0,20);
  centerDummy.add(bodyDummy);
  
  material = new THREE.MeshBasicMaterial({color : 0xffffff, envMap : bodyCubeMap});

  var body = new THREE.Mesh( geometry, material );
  //body.scale.set(3, 3, 3);
  body.position.set(0,5,0);
  body.rotateZ(90 * Math.PI / 180);
  bodyDummy.add(body);
  bodyDummy.add(bunnyDummy);
  
  noseDummy = new THREE.Object3D();
  bodyDummy.add(noseDummy);

  geometry = new THREE.CylinderGeometry( .5, 1,2,45 );
  material = new THREE.MeshBasicMaterial({color : 0xffffff, envMap : ourCubeMap});
  
  var nose = new THREE.Mesh( geometry, material );
  nose.position.set(6,5,0);
  nose.rotateZ(-90 * Math.PI / 180);
  noseDummy.add(nose);
  
  noseConeDummy = new THREE.Object3D();
  bodyDummy.add(noseConeDummy);
  
  
  const points = [];
	
	for ( let i = 0; i < 10; i ++ ) {
		points.push( new THREE.Vector2( Math.sin( i * 0.2 ) * 10 + 5, ( i - 5 ) * 2 ) );
	}

	
  geometry = new THREE.LatheGeometry(points);
  material = new THREE.MeshLambertMaterial( { color: 0xB0B5B5 } );
  var noseCone = new THREE.Mesh( geometry, material );
  noseCone.position.set(7.1,5,0);
  noseCone.scale.set(.034,.034,.034);
  noseCone.rotateZ(90 * Math.PI / 180);
  noseConeDummy.add(noseCone);
  
  wingDummy = new THREE.Object3D();
  bodyDummy.add(wingDummy);

  geometry = new THREE.BoxGeometry( .1,5,5 );
  var wing = new THREE.Mesh( geometry, material );
  wing.position.set(-2.2,5,0);
  wing.rotateZ(-90 * Math.PI / 180);
  wing.rotateX(-40 * Math.PI / 180);
  wingDummy.add(wing);
  
  tailDummy = new THREE.Object3D();
  bodyDummy.add(tailDummy);

  geometry = new THREE.BoxGeometry( .1,3,3 );
  var tail = new THREE.Mesh( geometry, material );
  tail.position.set(-3.2,5.9,0);
  tail.rotateY(-90 * Math.PI / 180);
  tail.rotateX(-40 * Math.PI / 180);
  tailDummy.add(tail);
  
  //thrusters
  
  thruster1Dummy = new THREE.Object3D();
  bodyDummy.add(thruster1Dummy);

  geometry = new THREE.CylinderGeometry( .25, .5,1.5,45 );
  var thruster1 = new THREE.Mesh( geometry, material );
  thruster1.position.set(-5,5.5,-.5);
  thruster1.rotateZ(-90 * Math.PI / 180);
  thruster1Dummy.add(thruster1);
  
  //thruster 2
  thruster2Dummy = new THREE.Object3D();
  bodyDummy.add(thruster2Dummy);

  geometry = new THREE.CylinderGeometry( .25, .5,1.5,45 );
  var thruster2 = new THREE.Mesh( geometry, material );
  thruster2.position.set(-5,5.4,.4);
  thruster2.rotateZ(-90 * Math.PI / 180);
  thruster2Dummy.add(thruster2);
  
  //thruster 3
  thruster3Dummy = new THREE.Object3D();
  bodyDummy.add(thruster3Dummy);

  geometry = new THREE.CylinderGeometry( .25, .5,1.5,45 );
  var thruster3 = new THREE.Mesh( geometry, material );
  thruster3.position.set(-5,4.5,0);
  thruster3.rotateZ(-90 * Math.PI / 180);
  thruster3Dummy.add(thruster3);
  
  
  
  
  //Flame 1
  flame1Dummy = new THREE.Object3D();
  bodyDummy.add(flame1Dummy);

  geometry = new THREE.TorusGeometry(3,.7,16,100);
  material = new THREE.MeshBasicMaterial({color : 0xffffff, envMap : bodyCubeMap});
  var flame1 = new THREE.Mesh( geometry, material );
  flame1.scale.set(.1,.1,.1);
  flame1.position.set(-6,4.5,0);
  flame1.rotateY(-90 * Math.PI / 180);
  flame1Dummy.add(flame1);
  
  //Flame 2
  flame2Dummy = new THREE.Object3D();
  bodyDummy.add(flame2Dummy);

  geometry = new THREE.TorusGeometry(3,.7,16,100);
  var flame2 = new THREE.Mesh( geometry, material );
  flame2.scale.set(.1,.1,.1);
  flame2.position.set(-6,5.5,-.5);
  flame2.rotateY(-90 * Math.PI / 180);
  flame2Dummy.add(flame2);
  
  //Flame 3
  flame3Dummy = new THREE.Object3D();
  bodyDummy.add(flame3Dummy);

  geometry = new THREE.TorusGeometry(3,.7,16,100);
  var flame3 = new THREE.Mesh( geometry, material );
  flame3.scale.set(.1,.1,.1);
  flame3.position.set(-6,5.4,.4);
  flame3.rotateY(-90 * Math.PI / 180);
  flame3Dummy.add(flame3);
  
  

	 scene.add( centerDummy );
	 
	  var light = new THREE.PointLight(0xffffff, 1.0);
	light.position.set(2, 3, 50);
	scene.add(light);

	light = new THREE.AmbientLight(0x555555);
	scene.add(light);
	
  let distance = 0;

  var render = function () {
	  distance += .01;
	  centerDummy.rotateY(.5 * Math.PI / 180);
	  // flame1.rotateZ(3 * Math.PI / 180);
	  // flame2.rotateZ(3 * Math.PI / 180);
	  // flame3.rotateZ(3 * Math.PI / 180);
	  
	  if(distance > .3){
	  distance = 0;
	  flame1.position.set(-6,4.5,0);
	  flame2.position.set(-6,5.5,-.5);
	  flame3.position.set(-6,5.4,.4);
	  }
  
	  flame1.translateZ(distance);
	  flame2.translateZ(distance);
	  flame3.translateZ(distance);
	  
	  
	// degrees += increment;
    // if (degrees >= 360) degrees = 0;
    // innerDegrees += 1;

    // let x = .75 * Math.cos(toRadians(degrees));
    // let z = .75 * Math.sin(toRadians(degrees));
	  
	// bodyDummy.translateX(x);
	// bodyDummy.translateZ(z);
	//bodyDummy.rotateY(innerDegrees);
	
    requestAnimationFrame( render );
     renderer.render(scene, camera);
  };

  render();
}

function loadOBJPromise(filename)
{
  var doResolve;
  var callback = function(loadedModel, materials)
  {
    // assume only one object in the .obj file
    var child = loadedModel.children[0];

    // for the new (2015) obj file loader, this is an instance
    // of THREE.BufferGeometry
    var geometry = child.geometry;
    doResolve(geometry);
  };

  return new Promise(function (resolve) {
    doResolve = resolve; // move into outer scope
    var objLoader = new THREE.OBJLoader();
    objLoader.load(modelFilename, callback);
    }
  );
}

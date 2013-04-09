This plugin integrates the well established [Barcode4j](http://barcode4j.sourceforge.net/ "Welcome to Barcode4J") library to provide convenient generation of barcodes. The version of Barcode4j that is used is 2.1.

## How it works

The plugin provides the `barcode4jService`. This service has methods for rendering a barcode image to an arbitrary output stream or to a http servlet response (convenience methods are also added to controllers to implicitly use the current response which is discussed later). These render methods take (among other options) a [`BarcodeGenerator`](http://barcode4j.sourceforge.net/2.0/javadocs/org/krysalis/barcode4j/BarcodeGenerator.html "BarcodeGenerator (barcode4j 2.0 API)") in the form of either a literal instance, or as the name of a `BarcodeGenerator` bean defined in the application context (i.e. `resources.groovy`).

### Generator Instances

For example, to generate a barcode to a PNG file using the [`Code39`](http://en.wikipedia.org/wiki/Code_39 "Code 39 - Wikipedia, the free encyclopedia") scheme you would do…

    import org.krysalis.barcode4j.impl.code39.Code39Bean
        
    // Create and configure the generator
    def generator = new Code39Bean()
    generator.height = 10
    
    def barcodeValue = "12345"
    def imageMimeType = "image/png"
    
    new File("barcode.png").withOutputStream { out ->
        barcode4jService.render(generator, barcodeValue, imageMimeType, out)
    }

The `barcode4jService` has convenience methods of `png`, `jpeg` and `gif` that configure the appopriate mime type for you. For example…

    new File("barcode.png").withOutputStream { out ->
        barcode4jService.png(generator, barcodeValue, out)
    }

### Generator Bean Names

It may be more convenient to manage the generator instance(s) via the application context. In `resources.groovy` we would have…

    import org.krysalis.barcode4j.impl.code39.Code39Bean
    
    beans = {
        code39Generator(Code39Bean) {
            height = 10
        }
    }

We can now refer to this generator by name when rendering…

    def barcodeValue = "12345"
    def imageMimeType = "image/png"
    
    new File("barcode.png").withOutputStream { out ->
        barcode4jService.render("code39Generator", barcodeValue, imageMimeType, out)
    }

The same `png`, `jpeg` and `gif` convenience methods exist for this variant.

### Rendering to the response

There are convenience methods added to all controller instances to render barcodes to the response. Our examples from above would become…

    import org.krysalis.barcode4j.impl.code39.Code39Bean
    
    class BarcodeController {
        def barcode = {
            // Create and configure the generator
            def generator = new Code39Bean()
            generator.height = 10

            def barcodeValue = "12345"

            renderBarcodePng(generator, barcodeValue)
        }
    }

Or alternatively using the generator bean defined in the application context…

    class BarcodeController {
        def barcode = {
            def barcodeValue = "12345"
            renderBarcodePng("code39Generator", barcodeValue)
        }
    }

The convenience methods are named: `renderBarcodePng`, `renderBarcodeJpeg` and `renderBarcodeGif`.

### Rendering Parameters

In addition to the individual configuration paramters for different barcode generators, there are `4` additional *optional* parameters that are configurable for a render. Each of these options has a corresponding default which is available as an instance variable of the `barcode4jService`, which allows you to override them globally at application startup (preferably via [property override configuration](http://grails.org/doc/latest/guide/14.%20Grails%20and%20Spring.html#14.6%20Property%20Override%20Configuration "14. Grails and Spring")).

    // Config.groovy
    beans {
        barcode4jService {
            defaultAntiAlias = true
        }
    }

To override them for a particual render operation, you can supply a map of the parameters as the **last** argument to **any** variant of the rendering methods.

For example:

    renderBarcodePng("code39Generator", barcodeValue, [antiAlias: true])

The parameters that are available…

#### dpi (type: `int`, default instance var name: `defaultDpi`, default value: `150`)

Controls the dots per inch parameter of the rendering canvas.

#### imageType (type: `int`, default instance var name: `defaultImageType`, default value: `java.awt.image.BufferedImage.TYPE_BYTE_BINARY`)

Controls the “type” of image to be rendered. See the constants of the [`BufferedImage`](http://download.oracle.com/javase/6/docs/api/java/awt/image/BufferedImage.html#field_summary "BufferedImage (Java Platform SE 6)") for more information.

#### antiAlias (type: `boolean`, default instance var name: `defaultAntiAlias`, default value: `false`)

Controls whether or not anti aliasing will be used for the rendering operation.

#### orientation (type: `int`, default instance var name: `defaultOrientation`, default value: `0`)

The orientation of barcode in degrees, rotated from the natural orientation of the barcode (typically left to right horizontal). For example, setting a value of `180` would render the barcode upside down.

## Why is there no controller or taglib?

The primary reason for this is to allow you as the developer to control access to the barcode generation in your application.

## Help / Support

Please raise issues on the Grails user mailing list.

## Licensing

[ASL 2.0](http://www.apache.org/licenses/LICENSE-2.0.html "Apache License, Version 2.0")

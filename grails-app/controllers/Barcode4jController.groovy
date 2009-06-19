import org.krysalis.barcode4j.impl.AbstractBarcodeBean
import org.krysalis.barcode4j.DefaultBarcodeClassResolver;
import org.krysalis.barcode4j.BarcodeClassResolver;
 

class Barcode4jController {

	def index = { redirect(action:example,params:params) }  

	def example = { }
}

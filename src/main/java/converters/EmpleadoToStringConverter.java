package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Empleado;

@Component
@Transactional
public class EmpleadoToStringConverter implements Converter<Empleado, String> {
	
	@Override
	public String convert(Empleado empleado) {
		String result;

		if (empleado == null)
			result = null;
		else
			result = String.valueOf(empleado.getId());

		return result;
	}

}

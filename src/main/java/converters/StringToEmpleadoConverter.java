package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Empleado;
import repositories.EmpleadoRepository;

@Component
@Transactional
public class StringToEmpleadoConverter implements Converter<String, Empleado> {

	@Autowired
	EmpleadoRepository empleadoRepository;

	@Override
	public Empleado convert(String text) {
		Empleado result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = empleadoRepository.findOne(id);
			}
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}

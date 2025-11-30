# Framework de Automatización API

Este framework permite la automatización de pruebas de APIs de manera robusta y escalable, facilitando la integración continua y la trazabilidad de pruebas gracias a su integración nativa con **Jira Xray** y ejecución automatizada en **GitHub Actions**. Está diseñado para que cualquier QA pueda adoptarlo fácilmente y ejecutar pruebas de API de forma eficiente.

---

## 🚀 ¿Por qué adoptar este framework?

- **Integración nativa con Jira Xray:** Publica automáticamente los resultados de tus pruebas en Jira Xray Cloud, asegurando la trazabilidad y el reporte de calidad.
- **Ejecución CI/CD con GitHub Actions:** Ejecuta tus pruebas en pipelines de GitHub Actions, permitiendo validaciones automáticas en cada Pull Request o despliegue.
- **Arquitectura escalable y mantenible:** Basado en Java, Maven y Karate, siguiendo buenas prácticas de automatización.
- **Fácil de usar y extender:** Pensado para que cualquier QA pueda crear, mantener y ejecutar pruebas rápidamente.

---

## 🏗️ Arquetipo del Proyecto

- **Lenguaje:** Java 17
- **Gestor de dependencias:** Maven
- **Frameworks:** Karate, JUnit 5
- **Integraciones:** Jira Xray Cloud, GitHub Actions

**Estructura principal:**
```
framework-automation-api/
├── src/
│   ├── main/
│   │   └── java/
│   │       ├── xray/              # Integración y utilidades para Jira Xray
│   │       └── utils/             # Utilidades generales y helpers
│   └── test/
│       ├── java/
│       │   ├── apis/              # Features y escenarios de prueba de API
│       │   │   ├── data/          # Archivos de datos de prueba (JSON, CSV, etc.)
│       │   │   ├── schema/        # Esquemas de validación de respuesta (JSON Schema)
│       │   │   └── ...            # Otros features y carpetas relacionadas a apis
│       │   ├── karate-config.js   # Configuración de entornos Karate
│       │   └── logback-test.xml   # Configuración de logs para Karate
│       └── resources/
│           └── configuracion/     # Configuración y properties (credenciales Xray/Jira)
├── .github/
│   └── workflows/                 # Workflows de GitHub Actions
├── pom.xml                        # Archivo de configuración Maven
└── README.md
```

- **xray/**: Lógica para integración y publicación de resultados en Jira Xray.
- **utils/**: Utilidades y helpers reutilizables.
- **apis/**: Features y escenarios de prueba de API escritos en Karate.
    - **data/**: Archivos de datos de prueba (por ejemplo, JSON, CSV).
    - **schema/**: Esquemas de validación de respuestas (JSON Schema).
- **karate-config.js**: Configuración de entornos y variables globales.
- **logback-test.xml**: Configuración de logs para la ejecución de pruebas Karate.
- **configuracion/**: Configuración de credenciales y parámetros de integración.
- **workflows/**: Workflows listos para CI/CD en GitHub Actions.

---

## ⚙️ Configuración Inicial

1. **Clona el repositorio:**
   ```bash
   git clone https://github.com/sergiogodoyp/framework-automation-api.git
   cd framework-automation-api
   ```

2. **Configura tus credenciales de Xray/Jira en** `src/test/resources/configuracion/config.properties`:
      ```properties
       xray.clientId=TU_CLIENT_ID
       xray.clientSecret=TU_CLIENT_SECRET
       xray.projectKey=SCRUM
   ```

3. **Instala las dependencias:**
   ```bash
   mvn clean install
   ```

---

## 🧪 Ejecución de Pruebas Local

Puedes ejecutar todos los tests con:
```bash
mvn test
```

Para ejecutar solo escenarios con un tag específico (por ejemplo, `@SCRUM-9`):
```bash
mvn test "-Dkarate.options=--tags @SCRUM-9"
```

Para cambiar el entorno (por ejemplo, `staging`):
```bash
mvn test "-Dkarate.env=staging"
```

---

## 🤖 Ejecución Automática en GitHub Actions

El proyecto incluye un workflow listo para usar en `.github/workflows/api-test.yml`.

**Ejecución manual desde GitHub:**

1. Ve a la pestaña **Actions** en tu repositorio.
2. Selecciona el workflow **Karate Tests**.
3. Haz clic en **Run workflow** y elige el entorno y los tags de Cucumber a ejecutar.

**Ejemplo de comando usado en el workflow:**
```yaml
mvn test "-Dkarate.env=${{ github.event.inputs.environment }}" "-Dkarate.options=--tags ${{ github.event.inputs.tags }}"
```

---

## 📊 Publicación de Resultados en Jira Xray

Al finalizar la ejecución, los resultados se publican automáticamente en Jira Xray Cloud, vinculando los escenarios ejecutados con los issues de Jira (usando los tags como `@SCRUM-9`, `@SCRUM-16`, etc.).

---

## 📝 Buenas Prácticas

- Usa tags de Jira en tus escenarios para trazabilidad.
- Mantén tus credenciales seguras y nunca las subas al repositorio.
- Revisa los reportes generados en `target/build/` y en Jira Xray tras cada ejecución.
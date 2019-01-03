from bottle import route, run
import os

@route('/')
def index():
    return '<br />'.join('{0} = {1}'.format(k, v) for k, v in os.environ.items())

if __name__ == '__main__':
    if os.getenv('VCAP_APP_PORT'):
        host = '0.0.0.0'
        port = int(os.getenv('VCAP_APP_PORT'))
    else:
        host = 'localhost'
        port = 8080

    run(host=host, port=port)
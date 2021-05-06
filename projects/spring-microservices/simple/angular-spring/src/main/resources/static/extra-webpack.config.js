const webpack = require('webpack')

module.exports = {
	plugins: [
		new webpack.DefinePlugin({
			'proc.env': {
				ENABLEDEBUGTOOLS: process.env.ENABLEDEBUGTOOLS,
				LOGLEVEL: JSON.stringify(process.env.LOGLEVEL)
			}
		})
	]
}